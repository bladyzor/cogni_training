package back.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateUtils {

    private static final String DATE_FORMAT = "yyyy-MM-dd";
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(DATE_FORMAT);

    public static String dateToString(final LocalDate date) {
        return date.format(DATE_TIME_FORMATTER);
    }

    public static LocalDate stringToDate(final String string) throws DateTimeParseException {
        return LocalDate.parse(string, DATE_TIME_FORMATTER);
    }
}