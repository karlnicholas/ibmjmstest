package ibmjmstest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ibmjmstest.integration.PurchaseOrderGateway;
import ibmjmstest.model.PurchaseOrder;

@RestController
public class PurchaseOrderController {

	@Autowired
	private PurchaseOrderGateway gateway;

	@RequestMapping(path = "/po")
	public PurchaseOrder index(@RequestParam Long id) {
		PurchaseOrder purchaseOrder = gateway.getPurchaseOrder(id);
		return purchaseOrder;
	}
}
