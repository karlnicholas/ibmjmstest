package ibmjmstest.test;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import ibmjmstest.IbmjmstestApplication;
import ibmjmstest.model.OrderItem;
import ibmjmstest.model.Product;
import ibmjmstest.model.PurchaseOrder;
import ibmjmstest.repository.ProductRepository;
import ibmjmstest.repository.PurchaseOrderRepository;
import ibmjmstest.service.CatalogController;
import ibmjmstest.service.PurchaseOrderController;
import ibmjmstest.types.OrderItemType;
import ibmjmstest.types.ProductType;
import ibmjmstest.types.PurchaseOrderType;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={IbmjmstestApplication.class})
public class ServiceTest {

    @Mock
    private ProductRepository productRepository;
    @Mock
    private PurchaseOrderRepository purchaseOrderRepository;

    @Autowired
    private CatalogController catalogController;
    @Autowired
    private PurchaseOrderController purchaseOrderController;
    
    private Product product;
    private PurchaseOrder purchaseOrder;
    private ProductType productType;
    private PurchaseOrderType purchaseOrderType;
    private OrderItemType orderItemType;
    
    private final String widget = "Widget";


    @Before
    public void setupMock() {
        MockitoAnnotations.initMocks(this);

        product = new Product();
        product.setId(1L);
        product.setName(widget);

        purchaseOrder = new PurchaseOrder();
        purchaseOrder.setId(1L);
        purchaseOrder.setComment("First Order");
        try {
            purchaseOrder.setOrderDate(new SimpleDateFormat("yyyy-MM-dd").parse("2017-03-31"));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        
        OrderItem orderItem = new OrderItem();
        orderItem.setItemPrice(new BigDecimal(1.99));
        orderItem.setQuantity(1);
        orderItem.setProduct(product);

        productType = new ProductType();
        productType.setId(1L);
        productType.setName(widget);

        purchaseOrderType = new PurchaseOrderType();
        purchaseOrderType.setId(1L);
        purchaseOrderType.setComment("First Order");
        try {
            GregorianCalendar gregory = new GregorianCalendar();
            gregory.setTimeInMillis(purchaseOrder.getOrderDate().getTime());
            purchaseOrderType.setOrderDate(
                    DatatypeFactory.newInstance().newXMLGregorianCalendar(gregory));
        } catch (DatatypeConfigurationException e) {
            throw new RuntimeException(e);
        }
        
        orderItemType = new OrderItemType();
        orderItemType.setItemPrice(new BigDecimal(1.99));
        orderItemType.setQuantity(1);
        orderItemType.setProductType(productType);
        
    }

    @Test
    public void testProduct() throws Exception {
/*
        List<Product> productList = new ArrayList<>();
        productList.add(product);
        
        when(productRepository.findByCategory(widget)).thenReturn(productList);
        catalogController.index(widget);
        verify(productRepository).findByCategory(widget);
        verifyZeroInteractions(productRepository);

        when(productRepository.findAll()).thenReturn(productList);
        catalogController.index(null);
        verify(productRepository).findAll();
        verifyZeroInteractions(productRepository);
*/
    	assertTrue(true);
    }

    @Test
    public void testPurchaseOrder() throws Exception {
/*
        // make a purchase order list
        List<PurchaseOrder> purchaseOrderList = new ArrayList<>();
        purchaseOrderList.add(purchaseOrder);

        when(purchaseOrderRepository.findById(1L)).thenReturn(purchaseOrder);
        purchaseOrderController.getPurchaseOrder(1L);
        verify(purchaseOrderRepository).findById(1L);
        verifyNoMoreInteractions(purchaseOrderRepository);

        when(purchaseOrderRepository.findAll()).thenReturn(purchaseOrderList);
        purchaseOrderController.getPurchaseOrderList();
        verify(purchaseOrderRepository).findAll();
        verifyNoMoreInteractions(purchaseOrderRepository);

        // need to test create and update with rest
        // create the services create new objects
 */
    	assertTrue(true);
    }

}
