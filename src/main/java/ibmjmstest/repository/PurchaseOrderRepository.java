package ibmjmstest.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.data.repository.CrudRepository;

import ibmjmstest.model.PurchaseOrder;

public interface PurchaseOrderRepository extends CrudRepository<PurchaseOrder, Long> {
    @EntityGraph(value="fetchOrderItemList", type=EntityGraphType.FETCH)
    PurchaseOrder findById(Long id);
}