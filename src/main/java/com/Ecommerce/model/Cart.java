package com.Ecommerce.model;

import static java.util.Collections.unmodifiableSet;
import java.util.HashSet;
import static java.util.Objects.*;
import java.util.Set;
import java.util.UUID;

import jakarta.validation.constraints.NotNull;

public final class Cart {
    
    private final UUID id;
    private final Client client;
    private final Set<ProductItem> items = new HashSet<>();

    private Cart(final UUID id,final Client client){
        this.id = id;
        this.client = client;
    }

    public static Cart create(final UUID id,final @NotNull Client client) {
        UUID newId = (id == null)? UUID.randomUUID(): id;
        return new Cart(newId, client);
    }
    
    public UUID getId() {
        return this.id;
    }

    public Client getUser() {
        return this.client;
    }

    public boolean add(@NotNull ProductItem item){
        return this.items.add(item);
    }

    public Set<ProductItem> getItems(){
        if(this.items.isEmpty()){
            throw new IllegalArgumentException("cart is empty");
        }
        return unmodifiableSet(new HashSet<>(this.items));
    }

    public boolean remove(@NotNull ProductItem item){
       return this.items.remove(item);
    }

    public void chargeQuantity(@NotNull ProductItem product, int newQuantity){
        if (newQuantity <= 0) throw new IllegalArgumentException("Quantity must be greater than 0");
        ProductItem newProduct = this.items.stream().filter(x -> x.equals(product)).findFirst().orElseThrow(() -> new IllegalArgumentException("Product not found in cart"));
        newProduct.setQuantity(newQuantity);
        this.add(newProduct);
    }

    public void clear(){
        this.items.clear();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cart cart = (Cart) o;
        return this.id.equals(cart.id);
    }

    @Override
    public int hashCode() {
        return hash(this.id, this.client);
    }

    @Override
    public String toString() {
        return "Cart{" +
                "id=" + this.id +
                ", client=" + this.client +
                ", items=" + this.items +
                '}';
    }

}