
package ibmjmstest.model;

import java.io.Serializable;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;

import ibmjmstest.types.OrderItemListType;
import ibmjmstest.types.PurchaseOrderType;

/**
 * PurchaseOrder model object.
 * 
 * @author Karl Nicholas
 * @version 2017-04-02
 *
 */
@Entity
public class PurchaseOrder implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private String comment;
    private Date orderDate;

    /**
     * Fill out properties from PurchaseOrderType. DOES NOT Copy all dependencies.   
     * @param purchaseOrderType
     * @return {@link PurchaseOrder} 
     */
    public PurchaseOrder fromPurchaseOrderType(PurchaseOrderType purchaseOrderType) {
        id = purchaseOrderType.getId();
        comment = purchaseOrderType.getComment();
        orderDate = purchaseOrderType.getOrderDate().toGregorianCalendar().getTime();
        return this;
    }

    /**
     * Create and return PurchaseOrderType representation. 
     * @return {@link PurchaseOrderType}
     * @throws DatatypeConfigurationException
     */
    public PurchaseOrderType asPurchaseOrderType() throws DatatypeConfigurationException {
        PurchaseOrderType purchaseOrderType = new PurchaseOrderType(); 
        purchaseOrderType.setId(id);
        purchaseOrderType.setComment(comment);
        // do the list of OrderItems
        OrderItemListType orderItemListType = new OrderItemListType();
        purchaseOrderType.setOrderItemListType(orderItemListType);
        // convert date
        //TODO: sort out date format in xsd
            GregorianCalendar gregory = new GregorianCalendar();
            gregory.setTimeInMillis(orderDate.getTime());
            purchaseOrderType.setOrderDate(
                    DatatypeFactory.newInstance().newXMLGregorianCalendar(gregory));
        return purchaseOrderType;
    }

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setId(Long value) {
        this.id = value;
    }

    /**
     * Gets the value of the comment property.
     * 
     * @return {@link String } comment 
     *     
     */
    public String getComment() {
        return comment;
    }

    /**
     * Sets the value of the comment property.
     * 
     * @param comment {@link String}
     *     
     */
    public void setComment(String comment) {
        this.comment = comment;
    }

    /**
     * Gets the value of the orderDate property.
     * 
     * @return {@link Date }
     *     
     */
    public Date getOrderDate() {
        return orderDate;
    }

    /**
     * Sets the value of the orderDate property.
     * 
     * @param orderDate {@link Date }
     *     
     */
    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }


}
