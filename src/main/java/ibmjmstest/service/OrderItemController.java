package ibmjmstest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ibmjmstest.integration.OrderItemGateway;
import ibmjmstest.model.OrderItem;

@RestController
public class OrderItemController {

	@Autowired
	private OrderItemGateway gateway;

	@RequestMapping(path = "/oiv")
	public List<OrderItem> index(@RequestParam Long purchaseOrderId) {
		List<OrderItem> orderItems = gateway.getOrderItems(purchaseOrderId);
		return orderItems;
	}
}
