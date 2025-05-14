package com.Ecommerce.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;
import java.util.UUID;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import com.Ecommerce.utils.Validator;

public final class Product {

    public static @NotNull Product create(@Nullable UUID id,
                                          @NotNull String name,
                                          @NotNull String description,
                                          @NotNull BigDecimal price,
                                          @NotNull Category category) {

        UUID newId = (id == null) ? UUID.randomUUID() : id;
        validateFields(name, description, price);
        return new Product(newId, name, description, price, category);
    }

    private static void validateFields(@NotNull String name,
                                       @NotNull String description,
                                       @NotNull BigDecimal price) {

        Validator.requireMinLength(name, 4, "Name");
        Validator.requireMinLength(description, 6, "Description");
        Validator.requireInRange(price, BigDecimal.ZERO, BigDecimal.valueOf(9999), "Price");
    }

    private final @NotNull UUID id;
    private final @NotNull String name;
    private final @NotNull String description;
    private final @NotNull BigDecimal price;
    private final @NotNull Category category;

    private Product(@NotNull UUID id,
                    @NotNull String name,
                    @NotNull String description,
                    @NotNull BigDecimal price,
                    @NotNull Category category) {

        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price.setScale(2, RoundingMode.HALF_UP);
        this.category = category;
    }

    public @NotNull UUID getId() {
        return this.id;
    }

    public @NotNull String getName() {
        return this.name;
    }

    public @NotNull String getDescription() {
        return this.description;
    }

    public @NotNull BigDecimal getPrice() {
        return this.price;
    }

    public @NotNull Category getCategory() {
        return this.category;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;
        Product product = (Product) o;
        return this.id.equals(product.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    @Override
    public @NotNull String toString() {
        return "Product{" +
               "id=" + this.id +
               ", name='" + this.name + '\'' +
               ", description='" + this.description + '\'' +
               ", price=" + this.price +
               ", category=" + this.category +
               '}';
    }
}
