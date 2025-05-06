package com.Ecommerce.model;

import java.math.BigDecimal;
import static java.util.Objects.hash;
import java.util.UUID;

import jakarta.validation.constraints.NotNull;

public final class ProductItem{

    private final UUID id;
    private final Product product;
    private int quantity;
    private final Cart cart;
    private final Order order;

    private ProductItem(final UUID id,final Product product,final int quantity,final Cart cart,final Order order){
        this.id = id;
        this.product = product;
        this.quantity = quantity;
        this.cart = cart;
        this.order = order;
    }
    public static ProductItem create(final UUID id,final @NotNull Product product,final int quantity,final @NotNull Cart cart,final @NotNull Order order){
        UUID newId = (id == null)? UUID.randomUUID():id;
        validateFields(quantity);
        return new ProductItem(newId,product,quantity,cart,order);
    }

    public UUID getId(){
        return this.id;
    }
    public Product getProduct(){
        return this.product;
    }
    public int getQuantity(){
        return this.quantity;
    }
    public void setQuantity(int quantity){
        if(quantity <= 0) throw new IllegalArgumentException("Quantity must be greater than 0");
        this.quantity = quantity;
    }
    public Cart getCart(){
        return this.cart;
    }
    public Order getOrder(){
        return this.order;
    }

    public BigDecimal getSubTotal(){
        return this.product.getPrice().multiply(BigDecimal.valueOf(quantity));
    }

    private static void validateFields(final int quantity) {
        if (quantity <= 0) throw new IllegalArgumentException("Quantity must be greater than 0");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProductItem)) return false;
        ProductItem that = (ProductItem) o;
        return this.id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return hash(this.id);
    }

    @Override
    public String toString() {
        return "ProductItem{" +
                "id=" + this.id +
                ", product=" + this.product +
                ", quantity=" + this.quantity +
                ", cart=" + this.cart +
                ", order=" + this.order +
                '}';
    }
}