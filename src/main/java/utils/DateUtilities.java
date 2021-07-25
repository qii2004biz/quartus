package utils;

import com.sun.istack.NotNull;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

public class DateUtilities {
    private DateUtilities() {

    }

    private static final DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("MM/dd/yyyy");
    private static final String DEFAULT_DATE_PATTERN = "MM/dd/yyyy";
    private static final DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HH:mm:ss");
    private static final DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss");

    public static String currentDate() {
        LocalDate date = LocalDate.now();
        return date.format(dateFormat);
    }

    public static String futureDate(int increment) {
        LocalDate date = LocalDate.now().plusDays(increment);
        return date.format(dateFormat);
    }

    public static String pastDate(int decrement) {
        LocalDate date = LocalDate.now().minusDays(decrement);
        return date.format(dateFormat);
    }

    public static String currentTime() {
        LocalDate date = LocalDate.now();
        return date.format(timeFormat);
    }

    public static String adjustDate(String pattern, int addDays) {

        ZonedDateTime date = ZonedDateTime.now().plusDays(addDays);

        return date.format(DateTimeFormatter.ofPattern(pattern));
    }

    @NotNull
    private static Calendar addDaysToDate(int days) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, days);
        return cal;
    }
}
