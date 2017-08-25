package ibmjmstest.service;

import java.util.List;

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
	public PurchaseOrderType index(@RequestParam Long purchaseOrderId) {
		try {
			PurchaseOrder purchaseOrder = purchaseOrderGateway.getPurchaseOrder(purchaseOrderId);
			List<OrderItem> orderItems = orderItemGateway.getOrderItems(purchaseOrderId);
			// convert to view
			PurchaseOrderType pot = purchaseOrder.asPurchaseOrderType();
			for ( OrderItem orderItem: orderItems ) {
				Product p = productGateway.getProduct(orderItem.getProductId());
				OrderItemType oit = orderItem.asOrderItemType();
				oit.setProductType(p.asProductType());
				pot.getOrderItemListType().getOrderItemType().add( oit );
			}
			return pot;
		} catch (DatatypeConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
