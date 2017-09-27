package ibmjmstest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ibmjmstest.repository.OrderItemRepository;
import ibmjmstest.types.OrderItemType;

@RestController
public class OrderItemController {

    @Autowired
    private OrderItemRepository repository;

    @RequestMapping(path = "/oiv")
    public OrderItemType index(@RequestParam Long id) {

        System.out.println("getOrderItems: " + Thread.currentThread().toString());
        return repository.findOne(id).asOrderItemType();
    }
}
