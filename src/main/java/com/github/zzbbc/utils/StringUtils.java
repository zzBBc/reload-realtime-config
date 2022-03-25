package com.github.zzbbc.utils;

public class StringUtils {
    public static String getPostfix(String value) {
        int lastIndex = value.lastIndexOf(".");

        return value.substring(lastIndex + 1, value.length());
    }
}
