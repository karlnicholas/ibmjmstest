package ibmjmstest.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

import ibmjmstest.types.OrderItemType;

/**
 * OrderItem model object
 * @author Karl Nicholas
 * @version 2017.04.02
 *
 */
@Entity
@IdClass(OrderItemPK.class)
public class OrderItem implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    private Long purchaseOrderId;
    @Id
    private Long productId;
    private int quantity;
    private BigDecimal itemPrice;
    @Column(nullable=false)
    private Long index;

    /**
     * Copies properties from @{link OrderItemType}. DOES NOT copy all dependencies.
     * @param orderItemType @{link OrderItemType}
     * @return {@link OrderItem}
     */
    public OrderItem fromOrderItemType(OrderItemType orderItemType ) {
        quantity = orderItemType.getQuantity();
        itemPrice = orderItemType.getItemPrice();
        return this;
    }
    
    /**
     * Create and return OrderItemType representation. 
     * @return {@link OrderItemType}
     */
    public OrderItemType asOrderItemType() {
        OrderItemType orderItemType = new OrderItemType();
        orderItemType.setQuantity(quantity);
        orderItemType.setItemPrice(itemPrice);
        return orderItemType;
    }

	public Long getPurchaseOrderId() {
		return purchaseOrderId;
	}

	public void setPurchaseOrderId(Long purchaseOrderId) {
		this.purchaseOrderId = purchaseOrderId;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public Long getIndex() {
		return index;
	}

	public void setIndex(Long index) {
		this.index = index;
	}

	/**
     * Gets the value of the quantity property.
     * 
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Sets the value of the quantity property.
     * 
     */
    public void setQuantity(int value) {
        this.quantity = value;
    }

    /**
     * Gets the value of the itemPrice property.
     * 
     * @return {@link BigDecimal }
     *     
     */
    public BigDecimal getItemPrice() {
        return itemPrice;
    }

    /**
     * Sets the value of the itemPrice property.
     * 
     * @param value {@link BigDecimal }
     *     
     */
    public void setItemPrice(BigDecimal value) {
        this.itemPrice = value;
    }
}
