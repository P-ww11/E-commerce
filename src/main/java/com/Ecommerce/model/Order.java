package com.Ecommerce.model;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import com.Ecommerce.utils.Validator;

import java.math.BigDecimal;
import java.util.*;

import static java.util.Collections.unmodifiableSet;
import static java.util.Objects.hash;

public final class Order {

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    public static @NotNull Order create(@Nullable UUID id,
                                        @NotNull Cart cart,
                                        @NotNull Address address,
                                        @NotNull Client client,
                                        @Nullable List<Discount> discounts,
                                        @NotNull PaymentTransaction PaymentTransaction,
                                        @NotNull PaymentMethod paymentMethod) {

        UUID newId = (id == null) ? UUID.randomUUID() : id;

        Validator.validateCollection(cart.toCollection());

        ZoneId customerZone = TimeZoneHelper.resolveZoneId(address.getContinent(),address.getCity());

        LocalDateTime orderTimestamp = LocalDateTime.ofInstant(Instant.now(),customerZone);

        return new Order(newId, cart, address, client, discounts,PaymentTransaction,paymentMethod, orderTimestamp);
    }

    private final @NotNull UUID id;
    private final @NotNull Set<ProductItem> items;
    private final @NotNull Address address;
    private final @NotNull Client client;
    private final @Nullable List<Discount> discounts;
    private final @NotNull PaymentTransaction paymentTransaction;
    private final @NotNull PaymentMethod paymentMethod;
    private final @NotNull LocalDateTime createdAt;

    private Order(@NotNull UUID id,
                  @NotNull Cart cart,
                  @NotNull Address address,
                  @NotNull Client client,
                  @Nullable List<Discount> discounts,
                  @NotNull PaymentTransaction paymentTransaction,
                  @NotNull PaymentMethod paymentMethod;
                  @NotNull LocalDateTime createdAt) {

        this.id = id;
        this.address = address;
        this.client = client;
        this.items = new HashSet<>(cart.toCollection());
        this.discounts = discounts != null ? new ArrayList<>(discounts) : null;
        this.paymentTransaction = paymentTransaction;
        this.paymentMethod = paymentMethod;
        this.createdAt = createdAt;
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

    public @NotNull Payment getPayment() {
        return this.payment;
    }

    public @NotNull LocalDateTime getTime(){
        return this.createdAt;
    }

    public @NotNull Set<ProductItem> getItems() {
        return unmodifiableSet(this.items);
    }

    public @Nullable List<Discount> getDiscounts() {
        return this.discounts;
    }

    public @NotNull getPaymentTransaction(){
        return this.paymentTransaction;
    }

    public @NotNull getPaymentMethod(){
        return this.paymentMethod;
    }

    /*public @NotNull BigDecimal calculateTotal(){
        BigDecimal subTotal = this.items.stream()
                .map(entity -> entity.getProduct().getPrice().multiply(BigDecimal.valueOf(entity.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
                
        BigDecimal total = this.discounts.stream()
                .map(discount -> discount.calculateDiscount(subTotal))
                .reduce(BigDecimal.ZERO, BigDecimal::add).subtract(subTotal);
        
        return;
    }*/

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
               ", createAt =" + this.createdAt.format(DATE_TIME_FORMATTER);
               ", payment=" + this.payment +
               '}';
    }
}
