package com.Ecommerce.model;

import static java.util.Collections.unmodifiableSet;
import java.util.HashSet;
import static java.util.Objects.hash;
import java.util.Set;
import java.util.UUID;
import org.jetbrains.annotations.NotNull;


public final class Order{

    private final UUID id;
    private final Set<Product> items;
    private final Address address;
    private final Client client;
    

    private Order(final UUID id,final Cart cart,final Address address,final Client client){
        this.id = id;
        this.address = address;
        this.client = client;
        this.items = new HashSet<>(cart.getItems().keySet());
    }

    public static Order create(final UUID id,final @NotNull Cart cart,final @NotNull Address address,final @NotNull Client client){
        UUID newId = (id == null)? UUID.randomUUID(): id;
        return new Order(newId, cart, address, client);
    }

    public UUID getId() {
        return this.id;
    }

    public Address getAddress() {
        return this.address;
    }

    public Client getClient() {
        return this.client;
    }

    public Set<Product> getItems(){
        if(this.items.isEmpty()){
            throw new IllegalArgumentException("order is empty");
        }
        return unmodifiableSet(new HashSet<>(this.items));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order)) return false;
        Order order = (Order) o;
        return this.id.equals(order.id);
    }

    @Override
    public int hashCode() {
        return hash(this.id);
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + this.id +
                ", items=" + this.items +
                ", address=" + this.address +
                ", client=" + this.client +
                '}';
    }
}