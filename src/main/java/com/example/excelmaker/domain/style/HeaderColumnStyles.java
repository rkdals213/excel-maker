package com.example.excelmaker.domain.style;

import org.apache.poi.ss.usermodel.CellStyle;

import java.util.HashMap;
import java.util.Map;

public class HeaderColumnStyles implements ColumnStyles{
    private final Map<String, CellStyle> styleMap = new HashMap<>();

    public void addStyle(String key, CellStyle cellStyle) {
        styleMap.put(key, cellStyle);
    }

    public CellStyle getStyle(String key) {
        return styleMap.get(key);
    }
}
