package net.bedra.maciej.mbjcommons.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

import lombok.extern.slf4j.Slf4j;

/**
 * Set of utils for real numbers given as Double Java type.
 *
 * @author Maciej Bedra
 */
@Slf4j
public class DoubleUtils {

    /**
     * Round real number to given decimal places.
     *
     * @param realNumber    real number
     * @param decimalPlaces quantity of result decimal places
     * @return double rounded real number to given decimal places
     */
    public static double round(double realNumber, int decimalPlaces) {
        log.debug("Rounding real number to decimal places [realNumber = {}, decimalPlaces = {}]", realNumber,
                decimalPlaces);
        BigDecimal bd = BigDecimal.valueOf(realNumber);
        bd = bd.setScale(decimalPlaces, RoundingMode.HALF_UP);
        realNumber = bd.doubleValue();
        log.debug("Real number rounded to decimal places [realNumber = {}]", realNumber);

        return realNumber;
    }

    /**
     * Convert text value to real number (two separators allowed: dot and comma).
     *
     * @param value real number in text representation
     * @return double text representation converted to real number
     * @throws NumberFormatException thrown when text representation is invalid
     * @throws NullPointerException  thrown when text representation is empty
     */
    public static double toDouble(String value) throws NumberFormatException, NullPointerException {
        log.debug("Converting text value to real number [value = {}]", value);
        value = value.replaceAll(",", ".");
        double real = new Double(value);
        log.debug("Text value converted to real number [real = {}]", real);

        return real;
    }

}