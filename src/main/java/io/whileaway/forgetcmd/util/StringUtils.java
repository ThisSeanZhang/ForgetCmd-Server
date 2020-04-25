package io.whileaway.forgetcmd.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
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

    public static String removeExtraSeparator(String str, String sep) {
        if (isEmptyOrBlank(str)) return str;
        return Stream.of(str.split(sep))
                .filter(StringUtils::nonEmptyOrBlank)
                .collect(Collectors.joining(sep));
    }

    public static String removeJsonStringExtraSeparator(String str, String sep) {
        if (isEmptyOrBlank(str)) return str;
        ObjectMapper mapper = new ObjectMapper();
        try {
            String primitiveStr = mapper.readValue(str, String.class);
            return removeExtraSeparator(primitiveStr, sep);
        } catch (IOException e) {
            e.printStackTrace();
            return str;
        }
    }


    public static void main(String[] args) throws IOException {
//        List<Long> longs = StringUtils.decodeIDs("1,2,3,4,5");
//        System.out.println(longs);
//        List<Long> longs2 = StringUtils.decodeIDs("NDM1OTgsNDU2NzgsNDUsNDUsNTYsNQ==");
//        System.out.println(longs2);

        String s = StringUtils.removeExtraSeparator("aaa----a-a-a-aa", "-");
        System.out.println(s);
        s = StringUtils.removeExtraSeparator("   podman aaaa  ", " ");
        System.out.println(s);

//        ObjectMapper mapper = new ObjectMapper();
//        String aa = "   dddd aaa ";
//        String s = mapper.writeValueAsString(aa);
//        System.out.println(s);
//        String s1 = mapper.readValue(" \"aaaa \"", String.class);
//        System.out.println(s1);
    }
}
