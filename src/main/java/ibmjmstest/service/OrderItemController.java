package ibmjmstest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ibmjmstest.integration.OrderItemGateway;
import ibmjmstest.types.OrderItemType;

@RestController
public class OrderItemController {

	@Autowired
	private OrderItemGateway gateway;

	@RequestMapping(path = "/oiv")
	public OrderItemType index(@RequestParam Long id) {
		OrderItemType oit = gateway.getOrderItemType(id);
		return oit;
	}
}
