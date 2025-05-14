package com.Ecommerce.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import com.Ecommerce.utils.Validator;

public final class PaymentTransaction {

    public static @NotNull PaymentTransaction create(@Nullable UUID id,
                                          @NotNull BigDecimal amount,
                                          @NotNull Currency currency,
                                          @NotNull LocalDateTime paymentDate,
                                          @NotNull Status status
                                          ) {
        UUID newId = (id == null) ? UUID.randomUUID() : id;
        validateFields(amount, currency, paymentDate, status);
        return new Payment(newId, amount, currency, paymentDate, status);
    }

    private static void validateFields(@NotNull BigDecimal amount,
                                       @NotNull Currency currency,
                                       @NotNull LocalDateTime paymentDate,
                                       @NotNull Status status) {

        Validator.requireInRange(amount, "amount");
        Validator.validateFutureDate(paymentDate, "payment_date");

        if (!Status.isValid(status.getCode())) {
            throw new IllegalArgumentException("Invalid status code: " + status.getCode());
        }
    }

    private final @NotNull UUID id;
    private final @NotNull BigDecimal amount;
    private final @NotNull Currency currency;
    private final @NotNull LocalDateTime paymentDate;
    private final @NotNull Status status;

    private PaymentTransaction(
        @NotNull UUID id,
        @NotNull BigDecimal amount, 
        @NotNull Currency currency,
        @NotNull LocalDateTime paymentDate, 
        @NotNull Status status) {
        
        this.id = id;
        this.amount = amount;
        this.currency = currency;
        this.paymentDate = paymentDate;
        this.status = status;
    }

    public @NotNull UUID getId() {
        return this.id;
    }

    public @NotNull BigDecimal getAmount() {
        return this.amount;
    }

    public @NotNull Currency getCurrency() {
        return this.currency;
    }

    public @NotNull LocalDateTime getPaymentDate() {
        return this.paymentDate;
    }

    public @NotNull Status getStatus() {
        return this.status;
    }

    public enum Status {

        PENDING(1),
        NOT_COMPLETED(2),
        COMPLETED(3);

        private final int code;

        Status(int code) {
            this.code = code;
        }

        public int getCode() {
            return this.code;
        }

        public static boolean isValid(int code) {
            return List.of(values())
                       .stream()
                       .anyMatch(status -> status.code == code);
        }
    }
}
