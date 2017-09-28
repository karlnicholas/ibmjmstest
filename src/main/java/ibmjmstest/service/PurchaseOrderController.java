package ibmjmstest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ibmjmstest.model.PurchaseOrder;
import ibmjmstest.repository.PurchaseOrderRepository;
import ibmjmstest.types.PurchaseOrderListType;
import ibmjmstest.types.PurchaseOrderType;

@RestController
public class PurchaseOrderController {

    @Autowired
    private PurchaseOrderRepository repository;

    // Basic operations for PurchaseOrder Service
 
    /**
     * REST endpoint for getPurchaseOrderList
     * /rest/purchaseorderservice/getpurchaseorderlist
     * @return {@link PurchaseOrderListType}
     */
    @RequestMapping(path = "getpurchaseorderlist", produces={"application/json"})
    public PurchaseOrderListType getPurchaseOrderList() throws Exception {
        PurchaseOrderListType purchaseOrderListType = new PurchaseOrderListType();
        for(PurchaseOrder purchaseOrder : repository.findAll() ){
            purchaseOrderListType.getPurchaseOrderType().add( purchaseOrder.asPurchaseOrderTypeShallow() );
        }
        return purchaseOrderListType;
    }

    /**
     * REST endpoint for getPurchaseOrder
     * /rest/purchaseorderservice/getpurchaseorder/{id}
     * @return {@link PurchaseOrderType}
     */
    @RequestMapping(path = "getpurchaseorder/{id}", produces={"application/json"})
    public PurchaseOrderType getPurchaseOrder(@PathVariable("id") Long id) throws Exception {
        // retrieve PurchaseOrder information based on the id supplied 
        PurchaseOrder purchaseOrder = repository.findById(id);            
        if ( purchaseOrder == null ) throw new IllegalArgumentException("PurchaseOrder not found for id: " + id);
        PurchaseOrderType purchaseOrderType = purchaseOrder.asPurchaseOrderTypeDeep();
        return purchaseOrderType;
    }
 
    /**
     * REST endpoint for addPurchaseOrder
     * /rest/purchaseorderservice/addpurchaseorder
     * @return {@link String}
     */
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
 
    /**
     * REST endpoint for updatepurchaseorder
     * /rest/purchaseorderservice/updatepurchaseorder
     * @return {@link String}
     */
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
