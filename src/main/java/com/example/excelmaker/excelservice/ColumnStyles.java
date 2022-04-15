package com.example.excelmaker.excelservice;

import org.apache.poi.ss.usermodel.CellStyle;

public interface ColumnStyles {
    void addStyle(String key, CellStyle cellStyle);

    CellStyle getStyle(String key);
}
