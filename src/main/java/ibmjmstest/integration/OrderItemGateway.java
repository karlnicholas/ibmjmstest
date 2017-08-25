package ibmjmstest.integration;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;

import ibmjmstest.types.OrderItemType;

@MessagingGateway
public interface OrderItemGateway {
	@Gateway(requestChannel = "orderItemChannel")
	OrderItemType getOrderItemType(Long id);
}