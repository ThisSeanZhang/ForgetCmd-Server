package io.whileaway.forgetcmd.util;

import java.util.List;
import java.util.Objects;

public class ListUtils {

    public static boolean isEmptyList(List list) {
        return Objects.isNull(list) || list.isEmpty();
    }

    public static boolean notEmptyList(List list) {
        return !isEmptyList(list);
    }
}
