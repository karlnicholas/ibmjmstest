
package ibmjmstest.test;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import ibmjmstest.IbmjmstestApplication;
import ibmjmstest.model.OrderItem;
import ibmjmstest.model.Product;
import ibmjmstest.model.PurchaseOrder;
import ibmjmstest.repository.ProductRepository;
import ibmjmstest.repository.PurchaseOrderRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={IbmjmstestApplication.class})
public class RepositoryTest
{
     
    @Autowired
    private ProductRepository productRepository;
     
    @Autowired
    private PurchaseOrderRepository purchaseOrderRepository;
     
    @Test
    public void testGetCatalog()
    {
    	
    	Iterable<Product> pl = productRepository.findAll();
        Set<Product> ps = new HashSet<>();
        for ( Product p: pl ) {
            ps.add(p);
        }
        assertEquals(4, ps.size());
    }
     
    @Test
    public void testGetProduct()
    {
        Product product = productRepository.findOne(1L);
        assertEquals("TEST ID", new Long(1), product.getId() );
        assertEquals("TEST SKU", "111-AA", product.getSku() );
        assertEquals("TEST NAME", "Widget", product.getName() );
        assertEquals("TEST DESCRIPTION", "Cool Widget", product.getDescription() );
        assertEquals("TEST CATEGORY", "Widget", product.getCategory());
    }

    @Test
    public void testGetPurchaseOrderList()
    {
        Set<PurchaseOrder> purchaseOrders = new HashSet<>();
        Iterable<PurchaseOrder> pit = purchaseOrderRepository.findAll();
        for (PurchaseOrder p: pit ) {
        	purchaseOrders.add(p);
        }
        assertEquals("TEST PURCHASEORDERLIST", 1, purchaseOrders.size());
    }

    @Test
    public void testGetPurchaseOrder()
    {
        PurchaseOrder purchaseOrder = purchaseOrderRepository.findById(1L);
        assertEquals("TEST ID", new Long(1), purchaseOrder.getId() );
        assertEquals("TEST COMMENT", "First Order, Yes!", purchaseOrder.getComment() );
        assertEquals("TEST ORDERLIST SIZE", 2, purchaseOrder.getOrderItemList().size() );
        try {
            assertEquals("TEST ORDERDATE", 
                new SimpleDateFormat("yyyy-MM-dd").parse("2017-03-31"), 
                purchaseOrder.getOrderDate() 
            );
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @Transactional
    @Rollback(true)
    public void testTnsertAndUpdatePurchaseOrder()
    {
        assertNull("TEST NO ID 2" , purchaseOrderRepository.findOne(2L));
        PurchaseOrder newPurchaseOrder = new PurchaseOrder();
        newPurchaseOrder.setOrderItemList(new ArrayList<>());
        newPurchaseOrder.setComment("Second Order");
        
        OrderItem newOrderItem1 = new OrderItem();
        newOrderItem1.setItemPrice(new BigDecimal(1.99));
        newOrderItem1.setQuantity(1);
        newOrderItem1.setProduct(productRepository.findOne(1L));        
        newPurchaseOrder.getOrderItemList().add(newOrderItem1);

        OrderItem newOrderItem2 = new OrderItem();
        newOrderItem2.setItemPrice(new BigDecimal(2.99));
        newOrderItem2.setQuantity(1);
        newOrderItem2.setProduct(productRepository.findOne(2L));        
        newPurchaseOrder.getOrderItemList().add(newOrderItem2);

        newPurchaseOrder.setComment("Second Order");
        try {
            newPurchaseOrder.setOrderDate(new SimpleDateFormat("yyyy-MM-dd").parse("2017-03-31"));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        assertNotNull( purchaseOrderRepository.save(newPurchaseOrder) );
        
        PurchaseOrder updatePurchaseOrder = purchaseOrderRepository.findById(2L);
        assertNotNull("TEST UPDATE PURCHASE ORDER", updatePurchaseOrder);
        updatePurchaseOrder.setComment(updatePurchaseOrder.getComment() + "Modified");
        updatePurchaseOrder.getOrderItemList().remove(1);
        assertNotNull( purchaseOrderRepository.save(updatePurchaseOrder) );

        assertNull("TEST NO ID 3" , purchaseOrderRepository.findOne(3L));

        PurchaseOrder purchaseOrder = purchaseOrderRepository.findById(2L);
        assertEquals("TEST ID", new Long(2), purchaseOrder.getId() );
        assertEquals("TEST COMMENT", "Second Order" + "Modified", purchaseOrder.getComment() );
        assertEquals("TEST ORDERLIST SIZE", 1, purchaseOrder.getOrderItemList().size() );
        try {
            assertEquals("TEST ORDERDATE", 
                new SimpleDateFormat("yyyy-MM-dd").parse("2017-03-31"), 
                purchaseOrder.getOrderDate() 
            );
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        
    }
}
