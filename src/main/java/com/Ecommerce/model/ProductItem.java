package com.Ecommerce.model;

import java.util.Objects;
import jakarta.validation.constraints.NotNull;

public final class ProductItem {

    public static ProductItem create(final @NotNull Product product, final int quantity) {
        validateFields(quantity);
        return new ProductItem(product, quantity);
    }
    private final @NotNull Product product;
    private final int quantity;

    private ProductItem(final @NotNull Product product, final int quantity) {
        this.product = product;
        this.quantity = quantity;
    }


    public @NotNull Product getProduct() {
        return this.product;
    }

    public int getQuantity() {
        return this.quantity;
    }

    private static void validateFields(final int quantity) {
        Validator.requirePositive(quantity, "Quantity");
    }

    @Override
    public @NotNull String toString() {
        return "ProductItem{" +
                "product=" + this.product +
                ", quantity=" + this.quantity +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProductItem)) return false;
        ProductItem that = (this) o;
        return quantity == that.quantity &&
               Objects.equals(this.product, that.product);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.product, this.quantity);
    }
}
