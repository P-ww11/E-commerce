package com.Ecommerce.model;

import static java.util.Collections.unmodifiableSet;
import static java.util.Objects.*;
import java.util.Map;
import java.util.HashMap;
import java.util.UUID;
import org.jetbrains.annotations.NotNull;
import java.math.BigDecimal;



public final class Cart {
    
    private final UUID id;
    private final Client client;
    private final Map<Product, Integer> items = new HashMap<>();

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

    public Client getClient() {
        return this.client;
    }

    public void put(@NotNull Product item, int newQuantity){
        Validator.requireInRage(newQuantity, 0, 100, "quantity");
        int quantity = items.getOrDefault(item, 0);
        this.items.put(item,quantity + newQuantity);
    }
    public void put(@NotNull Product item){
        int quantity = items.getOrDefault(item, 0);
        this.items.put(item, quantity + 1);
    }


    public Map<Product, Integer> getItems(){
        if(this.items.isEmpty()){
            throw new IllegalArgumentException("cart is empty");
        }
        return Map.copyOf(this.items);
    }

    public boolean remove(@NotNull Product item){
       return this.items.remove(item) != null;
    }
    public BigDecimal subTotal(){
        return this.getItems().entrySet().stream()
        .map(entry -> entry.getKey().getPrice().multiply(BigDecimal.valueOf(entry.getValue())))
        .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public void chargeQuantity(@NotNull Product product, int newQuantity){
        Validator.requirePositive(newQuantity, "quantity");
        this.put(product, newQuantity);
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