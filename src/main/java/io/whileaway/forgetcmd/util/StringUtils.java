package io.whileaway.forgetcmd.util;

import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StringUtils {
    public static boolean isEmptyOrBlank(String str) {
        return null == str || str.isEmpty() || str.isBlank();
    }

    public static boolean anyIsEmptyOrBlank(String... strs) {
        return Stream.of(strs).anyMatch(StringUtils::isEmptyOrBlank);
    }
}
