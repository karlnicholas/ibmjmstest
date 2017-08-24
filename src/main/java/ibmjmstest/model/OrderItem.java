package ibmjmstest.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * OrderItem model object
 * @author Karl Nicholas
 * @version 2017.04.02
 *
 */
@Entity
public class OrderItem implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private int quantity;
    private BigDecimal itemPrice;
    @Column(nullable=false)
    private Long productId;

    /**
     * Gets the value of the id property.
     * 
     * @return {@link Long }
     *     
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value {@link Long }
     *     
     */
    public void setId(Long value) {
        this.id = value;
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

    /**
     * Gets the id of the product.
     * 
     * @return Long
     *     
     */
    public Long getProductId() {
        return productId;
    }

    /**
     * Sets the id of the product.
     * 
     * @param value Long
     *     
     */
    public void setProductId(Long value) {
        this.productId = value;
    }
}
