package com.assertions;

import org.apache.commons.lang3.ObjectUtils;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

/**
 * Created by anitha on 02/09/2017.
 */
public class AssertUtils {

    public static void assertCurrencyStringsEqual(String expected, String actual) {
        assertCurrencyStringsEqual(new BigDecimal(expected), actual);
    }

    public static void assertCurrencyStringsEqual(BigDecimal expected, String actual) {
        assertEqualsViaCompareTo(expected, new BigDecimal(actual));
    }

    public static <T extends Comparable<? super T>> void assertEqualsViaCompareTo(T expected, T actual) {
        if (ObjectUtils.compare(expected, actual) != 0) {
            assertEquals(expected, actual); //That will give us a pretty message and failure.
        }
    }



}
