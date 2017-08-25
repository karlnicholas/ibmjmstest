package ibmjmstest.integration;

import java.util.List;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;

import ibmjmstest.model.OrderItem;

@MessagingGateway
public interface OrderItemGateway {
	@Gateway(requestChannel = "orderItemChannel")
	public List<OrderItem> getOrderItems(Long purchaseOrderId);
}