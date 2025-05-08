public final class ValidatorField{

     private Validator() {
        throw new UnsupportedOperationException("Validator is a utility class and cannot be instantiated");
    }

    public static void requireNonBlank(String value, String name) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException(name + " cannot be blank");
        }
    }
    public static void requirePositive(int value, String name) {
        if (value <= 0) {
            throw new IllegalArgumentException(name + " must be greater than 0");
        }
    }

    public static void requireInRange(int value, int min, int max, String name) {
        if (value < min || value > max) {
            throw new IllegalArgumentException(name + " must be between " + min + " and " + max);
        }
    }

    public static void requireNotFuture(LocalDate date, String fieldName) {
        if (date == null) {
            throw new IllegalArgumentException(fieldName + " cannot be null");
        }
        if (date.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException(fieldName + " cannot be in the future");
        }
    }

    public static void requireMinimumAge(LocalDate birthDate, int minimumAge, String fieldName) {
        if (birthDate == null) {
            throw new IllegalArgumentException(fieldName + " cannot be null");
        }
        int age = Period.between(birthDate, LocalDate.now()).getYears();
        if (age < minimumAge) {
            throw new IllegalArgumentException("User must be at least " + minimumAge + " years old");
        }
    }

     public static void requireMinLength(String value, int minLength, String fieldName) {
        if (value == null || value.isBlank() || value.length() < minLength) {
            throw new IllegalArgumentException(fieldName + " must be non-blank and at least " + minLength + " characters long");
        }
    }

    public static void requireInRange(BigDecimal value, BigDecimal min, BigDecimal max, String fieldName) {
        if (value == null || value.compareTo(min) <= 0 || value.compareTo(max) > 0) {
            throw new IllegalArgumentException(fieldName + " must be greater than " + min + " and less than or equal to " + max);
        }
    }
}