package ibmjmstest.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import javax.xml.datatype.DatatypeConfigurationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ibmjmstest.integration.OrderItemGateway;
import ibmjmstest.integration.ProductGateway;
import ibmjmstest.integration.PurchaseOrderGateway;
import ibmjmstest.model.OrderItem;
import ibmjmstest.model.Product;
import ibmjmstest.model.PurchaseOrder;
import ibmjmstest.types.OrderItemListType;
import ibmjmstest.types.OrderItemType;
import ibmjmstest.types.PurchaseOrderType;

@RestController
public class PurchaseOrderViewController {
	@Autowired
	private PurchaseOrderGateway purchaseOrderGateway;

	@Autowired
	private OrderItemGateway orderItemGateway;

	@Autowired
	private ProductGateway productGateway;

	@RequestMapping(path = "/pov")
	public PurchaseOrderType index(@RequestParam final Long purchaseOrderId) {
		PurchaseOrder purchaseOrder = null;
		List<OrderItem> orderItems = null;
		OrderItemListType oilt = new OrderItemListType(); 

		// manually do promises.
		final ExecutorService service = Executors.newFixedThreadPool(2);
		
		final Callable<PurchaseOrder> poThread = () -> {
			PurchaseOrder po = purchaseOrderGateway.getPurchaseOrder(purchaseOrderId);
			return po;
		};
		final Callable<List<OrderItem>> oiThread = ()-> {
			List<OrderItem> ois = orderItemGateway.getOrderItems(purchaseOrderId);
			return ois;
		};
		class ProductCallable implements Callable<OrderItemType> {
			private OrderItem orderItem;			
			public ProductCallable(OrderItem orderItem) {
				this.orderItem = orderItem;
			}
			@Override
			public OrderItemType call() throws Exception {
				Product p = productGateway.getProduct(orderItem.getProductId());
				OrderItemType oit = orderItem.asOrderItemType();
				oit.setProductType(p.asProductType());
				return oit;
			}
		};

		final Future<PurchaseOrder> poFuture = service.submit(poThread);
		final Future<List<OrderItem>> oiFuture = service.submit(oiThread);
		
		try {
			purchaseOrder = poFuture.get();
			orderItems = oiFuture.get();
		} catch (InterruptedException | ExecutionException e1) {
			e1.printStackTrace();
		}
		List<ProductCallable> productTasks = new ArrayList<>();
		
		for ( OrderItem orderItem: orderItems ) {
			ProductCallable pc = new ProductCallable(orderItem);
			productTasks.add(pc);
		}
		
		List<Future<OrderItemType>> ps = null;		
		try {
			ps = service.invokeAll(productTasks);
			for ( Future<OrderItemType> foit: ps ) {
				oilt.getOrderItemType().add( foit.get() );
			}
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
		
/*		
		purchaseOrder = purchaseOrderGateway.getPurchaseOrder(purchaseOrderId);
		orderItems = orderItemGateway.getOrderItems(purchaseOrderId);
		for ( OrderItem orderItem: orderItems ) {
			Product p = productGateway.getProduct(orderItem.getProductId());
			OrderItemType oit = orderItem.asOrderItemType();
			oit.setProductType(p.asProductType());
			oilt.getOrderItemType().add( oit );
		}
*/		
		try {
			// convert to view
			PurchaseOrderType pot = purchaseOrder.asPurchaseOrderType();
			pot.setOrderItemListType(oilt);
			return pot;
		} catch (DatatypeConfigurationException e) {
			e.printStackTrace();
		}
		return null;
	}
}
