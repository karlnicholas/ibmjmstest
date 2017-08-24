
package ibmjmstest.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OrderColumn;

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
    @ElementCollection(fetch=FetchType.EAGER)
    @OrderColumn(name="index")
    @Column(nullable=false)
    private List<Long> orderItemIds;
    private Date orderDate;

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
     * Gets the value of the orderItemLists property.
     * 
     * @return {@link OrderItemsType }
     *     
     */
    public List<Long> getOrderItemIds() {
        if ( orderItemIds == null ) {
            orderItemIds = new ArrayList<Long>();
        }
        return orderItemIds;
    }

    /**
     * Sets the value of the orderItemsType property.
     * 
     * @param orderItemList {@link OrderItemsType }
     *     
     */
    public void setOrderItemIds(List<Long> orderItemIds) {
        this.orderItemIds = orderItemIds;
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
