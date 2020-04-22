package io.whileaway.forgetcmd.util;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StringUtils {
    public static boolean isEmptyOrBlank(String str) {
        return null == str || str.isEmpty() || str.isBlank();
    }

    public static boolean nonEmptyOrBlank(String str) {
        return !isEmptyOrBlank(str);
    }

    public static boolean anyIsEmptyOrBlank(String... strs) {
        return Stream.of(strs).anyMatch(StringUtils::isEmptyOrBlank);
    }

    public static List<Long> decodeIDs(String idStr) {
        if (isEmptyOrBlank(idStr)) return new ArrayList<>();
        String ids = NumberUtils.isNumeric(idStr) || idStr.contains(",")
                ? idStr : new String(Base64.getDecoder().decode(idStr));
        return Arrays.stream(ids.split(","))
                .filter(StringUtils::nonEmptyOrBlank)
                .map(NumberUtils::str2Long)
                .collect(Collectors.toList());

    }

    public static void main(String[] args) {
        List<Long> longs = StringUtils.decodeIDs("1,2,3,4,5");
        System.out.println(longs);
        List<Long> longs2 = StringUtils.decodeIDs("NDM1OTgsNDU2NzgsNDUsNDUsNTYsNQ==");
        System.out.println(longs2);
    }
}
