package net.stringer.util;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;

public class Util {

    private Util() {}

    public static ZonedDateTime toZonedDateTime(String dateTime) {
        try {
            return ZonedDateTime.parse(dateTime, DateTimeFormatter.RFC_1123_DATE_TIME);
        } catch (DateTimeParseException dtpe) {
            return ZonedDateTime.ofInstant(Instant.parse(dateTime), ZoneId.systemDefault());
        }
    }

    public static String formatDateTime(ZonedDateTime zonedDateTime) {
        return DateTimeFormatter.ofPattern("EEEE, dd MMMM yyyy, HH:mm:ss", Locale.getDefault()).format(zonedDateTime);
    }
}
