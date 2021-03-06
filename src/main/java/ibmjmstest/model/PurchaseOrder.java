
package ibmjmstest.model;

import java.io.Serializable;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.NamedEntityGraphs;
import javax.persistence.NamedSubgraph;
import javax.persistence.OneToMany;
import javax.persistence.OrderColumn;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;

import ibmjmstest.types.OrderItemListType;
import ibmjmstest.types.OrderItemType;
import ibmjmstest.types.PurchaseOrderType;

/**
 * PurchaseOrder model object.
 * 
 * @author Karl Nicholas
 * @version 2017-04-02
 *
 */
@Entity
@NamedEntityGraphs({ 
	@NamedEntityGraph(
		name = "fetchOrderItemList", 
		attributeNodes = {
			@NamedAttributeNode(value = "orderItemList", subgraph = "fetchProduct") 
		}, 
		subgraphs = {
			@NamedSubgraph(
				name = "fetchProduct", 
				type = OrderItem.class, 
				attributeNodes = {
					@NamedAttributeNode(value = "product") 
				}
			) 
		}
	) 
})
public class PurchaseOrder implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String comment;
    private Date orderDate;
    @OneToMany(mappedBy="purchaseOrder", cascade=CascadeType.ALL)
    @OrderColumn
    private List<OrderItem> orderItemList;

    /**
     * Fill out properties from PurchaseOrderType. Copies all dependencies.   
     * @param purchaseOrderType
     * @return {@link PurchaseOrder} 
     */
    public PurchaseOrder fromPurchaseOrderType(PurchaseOrderType purchaseOrderType) {
        id = purchaseOrderType.getId();
        comment = purchaseOrderType.getComment();
        // do the list of OrderItems
        if ( purchaseOrderType.getOrderItemListType() != null ) {
            for ( OrderItemType orderItemType : purchaseOrderType.getOrderItemListType().getOrderItemType() ) {
                OrderItem orderItem = new OrderItem().fromOrderItemType(orderItemType);
                orderItemList.add(orderItem);
            }
        }
        orderDate = purchaseOrderType.getOrderDate().toGregorianCalendar().getTime();
        return this;
    }

    /**
     * Create and return PurchaseOrderType representation. 
     * @return {@link PurchaseOrderType}
     * @throws DatatypeConfigurationException
     */
    public PurchaseOrderType asPurchaseOrderTypeShallow() throws DatatypeConfigurationException {
        PurchaseOrderType purchaseOrderType = new PurchaseOrderType(); 
        purchaseOrderType.setId(id);
        purchaseOrderType.setComment(comment);
        // convert date
		GregorianCalendar gregory = new GregorianCalendar();
		gregory.setTimeInMillis(orderDate.getTime());
		purchaseOrderType.setOrderDate(DatatypeFactory.newInstance().newXMLGregorianCalendar(gregory));
        return purchaseOrderType;
    }

    /**
     * Create and return PurchaseOrderType representation. 
     * @return {@link PurchaseOrderType}
     * @throws DatatypeConfigurationException
     */
    public PurchaseOrderType asPurchaseOrderTypeDeep() throws DatatypeConfigurationException {
        PurchaseOrderType purchaseOrderType = new PurchaseOrderType(); 
        purchaseOrderType.setId(id);
        purchaseOrderType.setComment(comment);
        // do the list of OrderItems
        OrderItemListType orderItemListType = new OrderItemListType();
        for ( OrderItem orderItem : orderItemList) {
            OrderItemType orderItemType = orderItem.asOrderItemType();
            orderItemListType.getOrderItemType().add(orderItemType);
        }
        purchaseOrderType.setOrderItemListType(orderItemListType);
		// convert date
		// TODO: sort out date format in xsd
		GregorianCalendar gregory = new GregorianCalendar();
		gregory.setTimeInMillis(orderDate.getTime());
		purchaseOrderType.setOrderDate(DatatypeFactory.newInstance().newXMLGregorianCalendar(gregory));
        return purchaseOrderType;
    }

    /**
     * Gets the value of the id property.
     * 
     * @return possible object is {@link Long }
     * 
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *            allowed object is {@link Long }
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
     * @param comment
     *            {@link String}
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
     * @param orderDate
     *            {@link Date }
     * 
     */
    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public List<OrderItem> getOrderItemList() {
        return orderItemList;
    }

    public void setOrderItemList(List<OrderItem> orderItemList) {
        this.orderItemList = orderItemList;
    }

}
