package com.Ecommerce.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.UUID;

import static java.util.Objects.hash;
import org.jetbrains.annotations.NotNull;


public final class Product{
    private final UUID id;
    private final String name;
    private final String description;
    private final BigDecimal price;
    private final Category category;

    private Product(final UUID id,final String name,final String description,final BigDecimal price,final Category category){
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price.setScale(2, RoundingMode.HALF_UP);
        this.category = category;
    }

    public static Product create(final UUID id,final @NotNull String name,final @NotNull String description,final @NotNull BigDecimal price, final @NotNull Category category){
        UUID newId = (id == null)? UUID.randomUUID():id;
        validateFields(name,description,price);
        return new Product(newId,name,description,price, category);
    }

    public UUID getId(){
        return this.id;
    }
    public String getName(){
        return this.name;
    }
    public String getDescription(){
        return this.description;
    }
    public BigDecimal getPrice(){
        return this.price;
    }

    private static void validateFields(final String name,final String description,final BigDecimal price){
        Validator.requireMinLength(name, 4,"Name");
        Validator.requireMinLength(description, 6, "Description");
        Validator.requireInRange(price, BigDecimal.ZERO,BigDecimal.valueOf(9999), "Price");
    }

     @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return this.id.equals(product.id);
    }

    @Override
    public int hashCode() {
        return hash(this.id);
    }

    @Override
    public String toString() {
        return "Product{" +
               "id=" + this.id +
               ", name='" + this.name + '\'' +
               ", description='" + this.description + '\'' +
               ", price=" + this.price +
               ", category=" + this.category +
               '}';
    }
}