package com.ecommerce.model;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import com.ecommerce.utils.Validator;

public final class Cart {

    private static final int MAX_QUANTITY = 100;

    public static @NotNull Cart create(@Nullable UUID id, @NotNull Client client) {
        UUID newId = (id == null) ? UUID.randomUUID() : id;
        return new Cart(newId, client);
    }

    private final @NotNull UUID id;
    private final @NotNull Client client;
    private final @NotNull Map<Product, Integer> items = new HashMap<>();

    private Cart(@NotNull UUID id, @NotNull Client client) {
        this.id = id;
        this.client = client;
    }

    public @NotNull UUID getId() {
        return this.id;
    }

    public @NotNull Client getClient() {
        return this.client;
    }

    public @NotNull Set<ProductItem> toCollection() {
        return this.items.entrySet()
                .stream()
                .map(entry -> new ProductItem(entry.getKey(), entry.getValue()))
                .collect(Collectors.toSet());
    }

    public void put(@NotNull Product item, int newQuantity) {
        Validator.requireInRange(newQuantity, 0, MAX_QUANTITY, "quantity");
        int quantity = items.getOrDefault(item, 0);
        this.items.put(item, quantity + newQuantity);
    }

    public void put(@NotNull Product item) {
        this.put(item, 1);
    }

    public @NotNull Map<Product, Integer> getItems() {
        return Map.copyOf(this.items);
    }

    public @NotNull Map<String, Object> summary() {
        List<Map<String, Object>> products = this.items.entrySet()
                .stream()
                .map(e -> Map.of(
                        "product-name", e.getKey().getName(),
                        "quantity", e.getValue(),
                        "price", e.getKey().getPrice()
                ))
                .toList();

        return Map.of("items", products);
    }

    public boolean remove(@NotNull Product item) {
        return this.items.remove(item) != null;
    }

    public @NotNull BigDecimal subTotal() {
        return this.getItems().entrySet()
                .stream()
                .map(entry -> entry.getKey().getPrice().multiply(BigDecimal.valueOf(entry.getValue())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public void chargeQuantity(@NotNull Product product, int newQuantity) {
        Validator.requireInRange(newQuantity, 0, MAX_QUANTITY, "quantity");
        this.put(product, newQuantity);
    }

    public void clear() {
        this.items.clear();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cart)) return false;
        Cart cart = (Cart) o;
        return this.id.equals(cart.id);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(this.id, this.client);
    }

    @Override
    public @NotNull String toString() {
        return "Cart{" +
                "id=" + this.id +
                ", client=" + this.client +
                ", items=" + this.items +
                '}';
    }
}
