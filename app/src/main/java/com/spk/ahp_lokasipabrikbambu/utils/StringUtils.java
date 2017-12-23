package com.spk.ahp_lokasipabrikbambu.utils;

/**
 * Created by wisnu on 12/23/17.
 */

public class StringUtils {

    private static final String STRING_ENCODING_SEPARATOR = "-&@&-";

    public static String encodeStringPair(String left, String right) {
        return left + STRING_ENCODING_SEPARATOR + right;
    }

    public static String[] decodeStringPair(String encodedPair) {
        String[] strs = encodedPair.split(STRING_ENCODING_SEPARATOR);
        if (strs.length != 2) {
            throw new RuntimeException("invalid string pair encoding: " + encodedPair);
        }
        return strs;
    }

}
