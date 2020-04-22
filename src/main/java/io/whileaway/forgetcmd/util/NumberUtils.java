package io.whileaway.forgetcmd.util;

public class NumberUtils {

    public static Long str2Long(String str) {
        try {
            return Long.valueOf(str);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    public static Double str2Double(String str) {
        try {
            return Double.valueOf(str);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    public static Integer str2Integer(String str) {
        try {
            return Integer.valueOf(str);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    static boolean isNumeric(String idStr) {
        return StringUtils.nonEmptyOrBlank(idStr) && idStr.matches("^[0-9]+$");
    }
}
