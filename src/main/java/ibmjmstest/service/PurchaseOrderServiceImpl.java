package ibmjmstest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ibmjmstest.model.PurchaseOrder;
import ibmjmstest.repository.PurchaseOrderRepository;
import ibmjmstest.types.PurchaseOrderListType;
import ibmjmstest.types.PurchaseOrderType;

@RestController
public class PurchaseOrderServiceImpl implements PurchaseOrderService {

	@Autowired
    private PurchaseOrderRepository repository;

    public void setRepository(PurchaseOrderRepository repository) {
		this.repository = repository;
	}

    // Basic operations for PurchaseOrder Service
 
    /* (non-Javadoc)
	 * @see ibmjmstest.service.PurchaseOrderService#getPurchaseOrderList()
	 */
    @Override
	@RequestMapping(path = "getpurchaseorderlist", produces={"application/json"})
    public PurchaseOrderListType getPurchaseOrderList() throws Exception {
        PurchaseOrderListType purchaseOrderListType = new PurchaseOrderListType();
        for(PurchaseOrder purchaseOrder : repository.findAll() ){
            purchaseOrderListType.getPurchaseOrderType().add( purchaseOrder.asPurchaseOrderTypeShallow() );
        }
        return purchaseOrderListType;
    }

    /* (non-Javadoc)
	 * @see ibmjmstest.service.PurchaseOrderService#getPurchaseOrder(java.lang.Long)
	 */
    @Override
	@RequestMapping(path = "getpurchaseorder/{id}", produces={"application/json"})
    public PurchaseOrderType getPurchaseOrder(@PathVariable("id") Long id) throws Exception {
        // retrieve PurchaseOrder information based on the id supplied 
        PurchaseOrder purchaseOrder = repository.findById(id);            
        if ( purchaseOrder == null ) throw new IllegalArgumentException("PurchaseOrder not found for id: " + id);
        PurchaseOrderType purchaseOrderType = purchaseOrder.asPurchaseOrderTypeDeep();
        return purchaseOrderType;
    }
 
    /* (non-Javadoc)
	 * @see ibmjmstest.service.PurchaseOrderService#createPurchaseOrder(ibmjmstest.types.PurchaseOrderType)
	 */
    @Override
	@RequestMapping(
            path = "addpurchaseorder", 
            produces={"application/text"}, 
            consumes={"application/json"}, 
            method=RequestMethod.POST)
    public String createPurchaseOrder(PurchaseOrderType purchaseOrderType) throws Exception {
        // PurchaseOrder from PurchaseOrderType
        repository.save(new PurchaseOrder().fromPurchaseOrderType(purchaseOrderType));
        return "created";
    }
 
    /* (non-Javadoc)
	 * @see ibmjmstest.service.PurchaseOrderService#updatePurchaseOrder(ibmjmstest.types.PurchaseOrderType)
	 */
    @Override
	@RequestMapping(
            path = "addpurchaseorder", 
            produces={"application/text"}, 
            consumes={"application/json"}, 
            method=RequestMethod.PUT)
    public String updatePurchaseOrder(PurchaseOrderType purchaseOrderType) throws Exception {        
        // Find PurchaseOrder in the database 
        PurchaseOrder modifyPurchaseOrder = repository.findOne(purchaseOrderType.getId());
        if ( modifyPurchaseOrder == null ) 
            throw new IllegalArgumentException("PurchaseOrder not found for id: " + purchaseOrderType.getId());
        modifyPurchaseOrder.getOrderItemList().clear();
        modifyPurchaseOrder.fromPurchaseOrderType(purchaseOrderType);     
        // update PurchaseOrder info and return SUCCESS message
        repository.save(modifyPurchaseOrder);
        return "updated";
    }


}
