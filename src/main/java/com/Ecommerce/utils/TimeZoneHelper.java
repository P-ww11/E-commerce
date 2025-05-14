import org.jetbrains.annotations.NotNull;

import java.time.DateTimeException;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.stream.Collectors;

public final class TimeZoneHelper {

    public static @NotNull ZoneId resolveZoneId(final @NotNull String continent, final @NotNull String city) {
        String zoneIdString = getTimeZoneId(continent, city);

        try {
            return ZoneId.of(zoneIdString);
        } catch (DateTimeException e) {
            return ZoneId.systemDefault();
        }
    }

    private static @NotNull String getTimeZoneId(final @NotNull String continent, final @NotNull String city) {
        return formatZoneIdPart(continent, "continent") + "/" + formatZoneIdPart(city, "city");
    }

    private static @NotNull String formatZoneIdPart(final @NotNull String value, final @NotNull String fieldName) {
        Validator.requireNonBlank(value, fieldName);

        return Arrays.stream(value.trim().split("\\s+"))
            .map(part -> Character.toUpperCase(part.charAt(0)) + part.substring(1).toLowerCase())
            .collect(Collectors.joining("_"));
    }

    private TimeZoneHelper() {
        throw new UnsupportedOperationException("TimeZoneHelper is a utility class and cannot be instantiated");
    }
}
