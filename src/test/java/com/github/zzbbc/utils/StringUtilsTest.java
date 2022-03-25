package com.github.zzbbc.utils;

import org.junit.Test;

public class StringUtilsTest {
    @Test
    public void testGetPostfix() {
        String value = "get.properties";

        System.out.println(StringUtils.getPostfix(value));
    }
}
