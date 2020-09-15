package net.bedra.maciej.mbjcommons.util;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

/**
 * Tests for utils operating on real numbers.
 *
 * @author Maciej Bedra
 */
public class DoubleUtilsTest {

    private String commaTextRealNumber;
    private String commasTextRealNumber;
    private String dotsTextRealNumber;
    private double realNumber;
    private String textRealNumber;

    /**
     * Prepare data for testing.
     */
    @Before
    public void initialize() {
        commaTextRealNumber = "1,20609";
        commasTextRealNumber = "1,206,09";
        dotsTextRealNumber = "1.206.09";
        realNumber = 1.20609;
        textRealNumber = "1.20609";
    }

    /**
     * Test real numbers rounding to given decimal places.
     */
    @Test
    public void roundTest() {
        double noDecimalPlaces = DoubleUtils.round(realNumber, 0);
        double oneDecimalPlace = DoubleUtils.round(realNumber, 1);
        double twoDecimalPlaces = DoubleUtils.round(realNumber, 2);
        double fourDecimalPlaces = DoubleUtils.round(realNumber, 4);
        double unlockedDecimalPlaces = DoubleUtils.round(realNumber, 800);
        double negativeDecimalPlaces = DoubleUtils.round(realNumber, -2);
        assertEquals("No decimal places test failed", 1D, noDecimalPlaces, 0);
        assertEquals("One decimal place test failed", 1.20, oneDecimalPlace, 0);
        assertEquals("Two decimal places test failed", 1.21, twoDecimalPlaces, 0);
        assertEquals("Four decimal places test failed", 1.2061, fourDecimalPlaces, 0);
        assertEquals("Unlocked decimal places test failed", 1.206090, unlockedDecimalPlaces, 0);
        assertEquals("Negative decimal places test failed", 0D, negativeDecimalPlaces, 0);
    }

    /**
     * Test of text value conversion to real number.
     */
    @Test
    public void toDoubleTest() {
        double converted = DoubleUtils.toDouble(textRealNumber);
        double convertedComma = DoubleUtils.toDouble(commaTextRealNumber);
        assertEquals("Converting text value to real number test failed", realNumber, converted, 0);
        assertEquals("Converting comma text value to real number test failed", realNumber, convertedComma, 0);

        try {
            DoubleUtils.toDouble(null);
        } catch (Exception ex) {
            assertEquals("NullPointerException test failed", NullPointerException.class, ex.getClass());
        }

        try {
            DoubleUtils.toDouble(commasTextRealNumber);
        } catch (Exception ex) {
            assertEquals("NumberFormatException test failed", NumberFormatException.class, ex.getClass());
        }

        try {
            DoubleUtils.toDouble(dotsTextRealNumber);
        } catch (Exception ex) {
            assertEquals("NumberFormatException test failed", NumberFormatException.class, ex.getClass());
        }
    }

}