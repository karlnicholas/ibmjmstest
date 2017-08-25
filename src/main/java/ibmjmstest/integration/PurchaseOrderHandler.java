package ibmjmstest.integration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.stereotype.Service;

import ibmjmstest.model.PurchaseOrder;
import ibmjmstest.repository.PurchaseOrderRepository;

@Service
public class PurchaseOrderHandler {
	@Autowired
	private PurchaseOrderRepository repository;

	@ServiceActivator(inputChannel="purchaseOrderChannel")
	public PurchaseOrder getPurchaseOrder(Long id) {
		System.out.println( "getPurchaseOrder: " + Thread.currentThread().toString() );
		PurchaseOrder purchaseOrder = repository.findOne(id);
		return purchaseOrder;
	}

}
