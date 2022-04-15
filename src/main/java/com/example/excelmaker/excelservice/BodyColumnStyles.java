package com.example.excelmaker.excelservice;

import org.apache.poi.ss.usermodel.CellStyle;

import java.util.HashMap;
import java.util.Map;

public class BodyColumnStyles implements ColumnStyles{
    private final Map<String, CellStyle> styleMap = new HashMap<>();

    public void addStyle(String key, CellStyle cellStyle) {
        styleMap.put(key, cellStyle);
    }

    public CellStyle getStyle(String key) {
        return styleMap.get(key);
    }
}
