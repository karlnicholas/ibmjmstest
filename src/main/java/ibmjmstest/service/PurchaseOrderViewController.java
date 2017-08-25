package ibmjmstest.service;

import javax.xml.datatype.DatatypeConfigurationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ibmjmstest.integration.OrderItemGateway;
import ibmjmstest.integration.PurchaseOrderGateway;
import ibmjmstest.model.PurchaseOrder;
import ibmjmstest.types.OrderItemType;
import ibmjmstest.types.PurchaseOrderType;

@RestController
public class PurchaseOrderViewController {

	@Autowired
	private PurchaseOrderGateway purchaseOrderGateway;

	@Autowired
	private OrderItemGateway orderItemGateway;

	@RequestMapping(path = "/pov")
	public PurchaseOrderType index(@RequestParam Long id) {
		try {
			PurchaseOrder purchaseOrder = purchaseOrderGateway.getPurchaseOrder(id);
			PurchaseOrderType pot = purchaseOrder.asPurchaseOrderType();
			for ( Long oiId: purchaseOrder.getOrderItemIds() ) {
				OrderItemType oit = orderItemGateway.getOrderItemType(oiId);
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
