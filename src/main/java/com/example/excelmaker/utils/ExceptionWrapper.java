package com.example.excelmaker.utils;

import java.util.function.Function;

public class ExceptionWrapper {

    public static Object wrap(Object data ,Function<Object, Object> function) {
        return function.apply(data);
    }
}
