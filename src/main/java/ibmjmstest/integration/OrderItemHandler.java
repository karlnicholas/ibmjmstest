package ibmjmstest.integration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.stereotype.Service;

import ibmjmstest.model.OrderItem;
import ibmjmstest.model.Product;
import ibmjmstest.repository.OrderItemRepository;
import ibmjmstest.types.OrderItemType;

@Service
public class OrderItemHandler {
	@Autowired
	private OrderItemRepository repository;

	@Autowired
	private ProductGateway productGateway;

	@ServiceActivator(inputChannel="orderItemChannel")
	public OrderItemType getOrderItem(Long id) {
		OrderItem orderItem = repository.findOne(id);
		OrderItemType oit = orderItem.asOrderItemType();
		Product p = productGateway.getProduct( orderItem.getProductId() );
		oit.setProductType(p.asProductType());
		return oit;
	}
}
