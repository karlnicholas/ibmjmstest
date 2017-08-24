package ibmjmstest.integration;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;

import ibmjmstest.model.PurchaseOrder;

@MessagingGateway
public interface PurchaseOrderGateway {
	@Gateway(requestChannel = "purchaseOrderChannel")
	PurchaseOrder getPurchaseOrder(Long id);
}