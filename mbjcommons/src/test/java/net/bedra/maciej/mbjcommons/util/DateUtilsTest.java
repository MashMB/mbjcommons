package net.bedra.maciej.mbjcommons.util;

import org.junit.Before;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.assertEquals;

/**
 * Tests for utils processing dates.
 *
 * @author Maciej Bedra
 */
public class DateUtilsTest {

    private Date dateToFormat;
    private Date firstEverDate;
    private long milliseconds;
    private Date now;
    private Date nowMinusMonth;
    private Date nowMinusOneDay;
    private Date nowPlusWeek;
    private Date nowPlusYear;
    private Date oneMonthAgo;
    private Date oneYearAgo;
    private Date today;
    private Date twoYearsAgo;
    private Date yesterday;

    /**
     * Prepare data for testing.
     */
    @Before
    public void initialize() {
        Calendar cal = Calendar.getInstance();
        now = cal.getTime();
        cal.add(Calendar.DAY_OF_YEAR, -30);
        nowMinusMonth = cal.getTime();
        cal.add(Calendar.DAY_OF_YEAR, 30);
        cal.add(Calendar.DAY_OF_YEAR, -1);
        nowMinusOneDay = cal.getTime();
        cal.add(Calendar.DAY_OF_YEAR, 1);
        cal.add(Calendar.DAY_OF_YEAR, 7);
        nowPlusWeek = cal.getTime();
        cal.add(Calendar.DAY_OF_YEAR, -7);
        cal.add(Calendar.DAY_OF_YEAR, 365);
        nowPlusYear = cal.getTime();
        cal.add(Calendar.DAY_OF_YEAR, 365);
        cal.set(Calendar.DAY_OF_MONTH, 28);
        cal.set(Calendar.MONTH, 7);
        cal.set(Calendar.YEAR, 2019);
        cal.set(Calendar.HOUR_OF_DAY, 18);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        dateToFormat = cal.getTime();
        milliseconds = cal.getTimeInMillis();
        cal.set(Calendar.DAY_OF_MONTH, 1);
        cal.set(Calendar.MONTH, 0);
        cal.set(Calendar.YEAR, 1970);
        cal.set(Calendar.HOUR_OF_DAY, 1);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        firstEverDate = cal.getTime();
        cal.setTime(now);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        today = cal.getTime();
        cal.add(Calendar.DATE, -1);
        yesterday = cal.getTime();
        cal.add(Calendar.DATE, 1);
        cal.add(Calendar.DATE, -30);
        oneMonthAgo = cal.getTime();
        cal.add(Calendar.DATE, 30);
        cal.add(Calendar.DATE, -365);
        oneYearAgo = cal.getTime();
        cal.add(Calendar.DATE, 365);
        cal.add(Calendar.DATE, -730);
        twoYearsAgo = cal.getTime();
        cal.add(Calendar.DATE, 730);
    }

    /**
     * Test calculation days between two dates.
     */
    @Test
    public void daysBetweenTest() {
        long zeroDays = DateUtils.daysBetween(now, now);
        long oneDay = DateUtils.daysBetween(nowMinusOneDay, now);
        long sevenDays = DateUtils.daysBetween(now, nowPlusWeek);
        long thirtyDays = DateUtils.daysBetween(now, nowMinusMonth);
        long year = DateUtils.daysBetween(nowPlusYear, now);
        assertEquals("Calculating days between two the same dates test failed", 0, zeroDays);
        assertEquals("Calculating days between now subtract one day test failed", 1, oneDay);
        assertEquals("Calculating days between now add week test failed", 7, sevenDays);
        assertEquals("Calculating days between now subtract month test failed", 30, thirtyDays);
        assertEquals("Calculating days between now add year test failed", 365, year);

        try {
            DateUtils.daysBetween(null, null);
        } catch (Exception ex) {
            assertEquals("NullPointerException test failed", NullPointerException.class, ex.getClass());
        }
    }

    /**
     * Test of milliseconds conversion to date.
     */
    @Test
    public void toDateTest() {
        Date correctDate = DateUtils.toDate(milliseconds);
        Date firstDate = DateUtils.toDate(0);
        assertEquals("Parsing milliseconds to date test failed", dateToFormat, correctDate);
        assertEquals("Parsing milliseconds to first ever date test failed", firstEverDate, firstDate);
    }

    /**
     * Test converting date objects to text representation with given formatting.
     */
    @Test
    public void testToFormattedString() {
        String iso8601Format = DateUtils.toFormattedString(dateToFormat);
        String defaultFormat = DateUtils.toFormattedString(dateToFormat, null);
        SimpleDateFormat simpleFormat = new SimpleDateFormat("dd.MM.yyyy");
        String simpleFormatted = DateUtils.toFormattedString(dateToFormat, simpleFormat);
        assertEquals("ISO8601 date formatting test failed", "2019-08-28 18:00:00", iso8601Format);
        assertEquals("Default date formatting test failed", "2019-08-28 18:00:00", defaultFormat);
        assertEquals("Simple date formatting test failed", "28.08.2019", simpleFormatted);

        try {
            DateUtils.toFormattedString(null, null);
        } catch (Exception ex) {
            assertEquals("NullPointerException test failed", NullPointerException.class, ex.getClass());
        }

        try {
            SimpleDateFormat wrongFormat = new SimpleDateFormat("wrong");
            DateUtils.toFormattedString(dateToFormat, wrongFormat);
        } catch (Exception ex) {
            assertEquals("IllegalArgumentException test failed", IllegalArgumentException.class, ex.getClass());
        }
    }

    /**
     * Test for date conversion to milliseconds value.
     */
    @Test
    public void toMillisecondsTest() {
        long millis = DateUtils.toMilliseconds(dateToFormat);
        assertEquals("Converting date to milliseconds test failed", milliseconds, millis);

        try {
            DateUtils.toMilliseconds(null);
        } catch (Exception ex) {
            assertEquals("NullPointerException test failed", NullPointerException.class, ex.getClass());
        }
    }

    /**
     * Test for subtracting days from date.
     */
    @Test
    public void subtractDaysTest() {
        assertEquals("Subtracting one day from today test failed", yesterday, DateUtils.subtractDays(today, 1));
        assertEquals("Subtracting one month from today test failed", oneMonthAgo, DateUtils.subtractDays(today, 30));
        assertEquals("Subtracting one year from today test failed", oneYearAgo, DateUtils.subtractDays(today, 365));
        assertEquals("Subtracting two years from today test failed", twoYearsAgo, DateUtils.subtractDays(today, 730));

        try {
            DateUtils.subtractDays(null, 0);
        } catch (Exception ex) {
            assertEquals("NullPointerException test failed", NullPointerException.class, ex.getClass());
        }
    }

}