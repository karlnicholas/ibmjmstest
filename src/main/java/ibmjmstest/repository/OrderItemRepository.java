package ibmjmstest.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ibmjmstest.model.OrderItem;

public interface OrderItemRepository extends CrudRepository<OrderItem, Long> {

	List<OrderItem> findAllByPurchaseOrderId(Long purchaseOrderId);

}