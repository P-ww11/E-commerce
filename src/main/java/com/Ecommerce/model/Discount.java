package com.ecommerce.model;

import com.ecommerce.utils.Validator;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

public final class Discount {

    private static final BigDecimal MAX_PERCENTAGE = BigDecimal.valueOf(100);

    private final @NotNull BigDecimal percentage;
    private @NotNull LocalDate expirationDate;

    public Discount(@NotNull BigDecimal percentage,
                    @NotNull LocalDate expirationDate) {

        Validator.requireInRange(percentage, BigDecimal.ZERO, MAX_PERCENTAGE, "Percentage");
        Validator.validateFutureDate(expirationDate, "Expiration date");

        this.percentage = percentage;
        this.expirationDate = expirationDate;
    }

    public @NotNull BigDecimal getPercentage() {
        return percentage;
    }

    public @NotNull LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(@NotNull LocalDate newDate) {
        Validator.validateFutureDate(newDate, "New expiration date");
        this.expirationDate = newDate;
    }

    public boolean isValid() {
        return !expirationDate.isBefore(LocalDate.now());
    }

    public @NotNull BigDecimal calculateDiscount(@NotNull BigDecimal price) {

        if (!isValid()) return Bigdecimal.ZERO;

        BigDecimal discount = price.multiply(percentage.divide(BigDecimal.valueOf(100)));

        if (discount.compareTo(price) >= 0) return BigDecimal.ZERO;

        return discount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Discount)) return false;
        Discount discount = (Discount) o;
        return percentage.equals(discount.percentage) &&
               expirationDate.equals(discount.expirationDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(percentage, expirationDate);
    }

    @Override
    public @NotNull String toString() {
        return "Discount{" +
               "percentage=" + percentage +
               ", expirationDate=" + expirationDate +
               '}';
    }
}
