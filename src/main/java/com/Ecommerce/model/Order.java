package com.ecommerce.model;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.math.BigDecimal;
import java.util.*;

import static java.util.Collections.unmodifiableSet;
import static java.util.Objects.hash;

public final class Order {

    public static @NotNull Order create(@Nullable UUID id,
                                        @NotNull Cart cart,
                                        @NotNull Address address,
                                        @NotNull Client client,
                                        @Nullable List<Discount> discounts,
                                        @NotNull Payment payment) {

        UUID newId = (id == null) ? UUID.randomUUID() : id;
        Validator.validateCollection(cart.toCollection());
        return new Order(newId, cart, address, client, discounts, payment);
    }

    private final @NotNull UUID id;
    private final @NotNull Set<ProductItem> items;
    private final @NotNull Address address;
    private final @NotNull Client client;
    private final @Nullable List<Discount> discounts;
    private final @NotNull Payment payment;

    private Order(@NotNull UUID id,
                  @NotNull Cart cart,
                  @NotNull Address address,
                  @NotNull Client client,
                  @Nullable List<Discount> discounts,
                  @NotNull Payment payment) {

        this.id = id;
        this.address = address;
        this.client = client;
        this.items = new HashSet<>(cart.toCollection());
        this.discounts = discounts != null ? new ArrayList<>(discounts) : null;
        this.payment = payment;
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

    public @NotNull Set<ProductItem> getItems() {
        return unmodifiableSet(new HashSet<>(this.items));
    }

    public @Nullable List<Discount> getDiscounts() {
        return this.discounts != null ? Collections.unmodifiableList(this.discounts) : null;
    }

    public @NotNull Payment getPayment() {
        return this.payment;
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
               ", payment=" + this.payment +
               '}';
    }
}
