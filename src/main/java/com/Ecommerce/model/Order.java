package com.Ecommerce.model;

import static java.util.Collections.unmodifiableSet;
import java.util.HashSet;
import static java.util.Objects.hash;
import java.util.Set;
import java.util.UUID;
import org.jetbrains.annotations.NotNull;


public final class Order{

    public static @NotNull Order create(final @Nullable UUID id,final @NotNull Cart cart,final @NotNull Address address,final @NotNull Client client){
        UUID newId = (id == null)? UUID.randomUUID(): id;
        return new Order(newId, cart, address, client);
    }
    private final @NotNull UUID id;
    private final @NotNull Set<ProductItem> items;
    private final @NotNull Address address;
    private final @NotNull Client client;
    

    private Order(final @NotNull UUID id,final @NotNull Cart cart,final @NotNull Address address,final @NotNull Client client){
        this.id = id;
        this.address = address;
        this.client = client;
        this.items = new HashSet<>(cart.toCollection());
    }


    public @NotNull UUID getId() {
        return this.id;
    }

    public @NotNull Address getAddress() {
        return this.address;
    }

    public @NotNull Client getClient() {
        return this.client;
    }

    public @NotNull Set<Product> getItems(){
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
    public @NotNull String toString() {
        return "Order{" +
                "id=" + this.id +
                ", items=" + this.items +
                ", address=" + this.address +
                ", client=" + this.client +
                '}';
    }
}