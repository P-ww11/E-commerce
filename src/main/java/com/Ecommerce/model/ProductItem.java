package com.ecommerce.model;

import java.util.Objects;
import org.jetbrains.annotations.NotNull;

import com.ecommerce.utils.Validator;

class ProductItem {

    private final @NotNull Product product;
    private final int quantity;

    public ProductItem(@NotNull Product product, int quantity) {
        Validator.requirePositive(quantity, "Quantity");
        this.product = product;
        this.quantity = quantity;
    }

    public @NotNull Product getProduct() {
        return this.product;
    }

    public int getQuantity() {
        return this.quantity;
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
        ProductItem that = (ProductItem) o;
        return this.quantity == that.quantity &&
               Objects.equals(this.product, that.product);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.product, this.quantity);
    }
}
