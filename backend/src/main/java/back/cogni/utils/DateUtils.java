package back.cogni.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

    private static final String DATE_FORMAT = "yyyy-MM-dd";
    private static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat(DATE_FORMAT);

    public static String dateToString(final Date date) {
        return SIMPLE_DATE_FORMAT.format(date);
    }

    public static Date stringToDate(final String string) throws ParseException {
        return SIMPLE_DATE_FORMAT.parse(string);
    }
}