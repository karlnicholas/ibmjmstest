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

	@ServiceActivator(inputChannel="orderItemChannel")
	public List<OrderItem> getOrderItems(Long purchaseOrderId) {
		System.out.println( "getOrderItems: " + Thread.currentThread().toString() );
		List<OrderItem> orderItems = repository.findAllByPurchaseOrderId(purchaseOrderId);
		return orderItems;
	}
}
