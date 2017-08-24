package ibmjmstest.repository;

import org.springframework.data.repository.CrudRepository;

import ibmjmstest.model.PurchaseOrder;

public interface PurchaseOrderRepository extends CrudRepository<PurchaseOrder, Long> {

}