public interface PaymentMethod{

    void processPayment(@NotNull BigDecimal amount);
    @NotNull Currency getCurrency();
    @NotNull BigDecimal calculateFee(@NotNull BigDecimal amount);
    @NotNull String getType();
    @NotNull String getMaskedDetails();
    boolean isValid();
    @NotNull String getProviderName();

}