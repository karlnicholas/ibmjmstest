package ibmjmstest.repository;

import org.springframework.data.repository.CrudRepository;

import ibmjmstest.model.OrderItem;

public interface OrderItemRepository extends CrudRepository<OrderItem, Long> {

}