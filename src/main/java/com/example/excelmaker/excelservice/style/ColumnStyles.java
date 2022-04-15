package com.example.excelmaker.excelservice.style;

import org.apache.poi.ss.usermodel.CellStyle;

public interface ColumnStyles {
    void addStyle(String key, CellStyle cellStyle);

    CellStyle getStyle(String key);
}
