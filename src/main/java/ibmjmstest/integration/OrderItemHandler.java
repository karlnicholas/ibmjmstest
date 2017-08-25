package ibmjmstest.integration;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.stereotype.Service;

import ibmjmstest.model.OrderItem;
import ibmjmstest.repository.OrderItemRepository;

@Service
public class OrderItemHandler {
	@Autowired
	private OrderItemRepository repository;

	@Autowired
	private ProductGateway productGateway;

	@ServiceActivator(inputChannel="orderItemChannel")
	public List<OrderItem> getOrderItems(Long purchaseOrderId) {
		List<OrderItem> orderItems = repository.findAllByPurchaseOrderId(purchaseOrderId);
		return orderItems;
	}
}
