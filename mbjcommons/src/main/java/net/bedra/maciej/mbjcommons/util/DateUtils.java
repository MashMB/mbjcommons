package net.bedra.maciej.mbjcommons.util;

import lombok.extern.slf4j.Slf4j;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;

/**
 * Set of utils for dates processing.
 *
 * @author Maciej Bedra
 */
@Slf4j
public class DateUtils {

    public static SimpleDateFormat ISO8601Format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * Calculates days between two dates.
     *
     * @param fromDate date that indicate beginning of period
     * @param toDate   date that indicate end of period
     * @return long difference between dates in days
     * @throws NullPointerException exception thrown when one of the dates is empty
     */
    public static long daysBetween(Date fromDate, Date toDate) throws NullPointerException {
        log.debug("Calculating days between dates [fromDate = {}, toDate = {}]", fromDate, toDate);
        LocalDate convertedFromDate = fromDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate convertedToDate = toDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        long days = Math.abs(ChronoUnit.DAYS.between(convertedFromDate, convertedToDate));
        log.debug("Days between dates calculated [days = {}]", days);

        return days;
    }

    /**
     * Convert milliseconds to date.
     *
     * @param milliseconds date saved in milliseconds
     * @return Date milliseconds converted to date
     */
    public static Date toDate(long milliseconds) {
        log.debug("Converting milliseconds to date [milliseconds = {}]", milliseconds);
        Date date = new Date(milliseconds);
        log.debug("Milliseconds converted to date [date = {}]", date);

        return date;
    }

    /**
     * Convert Date to String with usage of default ISO8601 formatting (yyyy-MM-dd
     * HH:mm:ss).
     *
     * @param date date to convert and format
     * @return String converted date and formatted to default ISO8601 format
     * @throws NullPointerException thrown when date not given
     */
    public static String toFormattedString(Date date) throws NullPointerException {
        log.debug("Converting Date to ISO8601 formatted String [date = {}, ISO8601 format = {}]", date,
                ISO8601Format.toPattern());
        String formatted = ISO8601Format.format(date);
        log.debug("Date converted to formatted ISO8601 String [formatted = {}]", formatted);

        return formatted;
    }

    /**
     * Convert Date to String with usage of given formatting pattern. If formatting
     * pattern is not specified, default ISO8601 format (yyyy-MM-dd HH:mm:ss) will
     * be used.
     *
     * @param date   date to convert and format
     * @param format output formatting pattern
     * @return String converted date with given formatting
     * @throws IllegalArgumentException thrown when given formatting pattern is
     *                                  invalid
     * @throws NullPointerException     thrown when date not given
     */
    public static String toFormattedString(Date date, SimpleDateFormat format)
            throws IllegalArgumentException, NullPointerException {
        format = format != null ? format : ISO8601Format;
        log.debug("Converting Date to formatted String [date = {}, format = {}]", date, format.toPattern());
        String formatted = format.format(date);
        log.debug("Date converted to formatted String [formatted = {}]", formatted);

        return formatted;
    }

    /**
     * Convert date to milliseconds.
     *
     * @param date date to convert
     * @return long date representation as milliseconds
     * @throws NullPointerException thrown when given date is empty
     */
    public static long toMilliseconds(Date date) throws NullPointerException {
        log.debug("Converting date to milliseconds [date = {}]", date);
        long milliseconds = date.getTime();
        log.debug("Date converted to milliseconds [milliseconds = {}]", milliseconds);

        return milliseconds;
    }

    /**
     * Subtract number of days from given date.
     *
     * @param date date from which days will be subtracted
     * @param days number of days to subtract
     * @return Date date with subtracted days
     * @throws NullPointerException thrown when given date is empty
     */
    public static Date subtractDays(Date date, int days) throws NullPointerException {
        log.debug("Subtracting days from date [date = {}, days = {}]", date, days);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.add(Calendar.DATE, -days);
        date = calendar.getTime();
        log.debug("Days subtracted from date [date = {}]", date);

        return date;
    }

}