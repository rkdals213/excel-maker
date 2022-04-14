package com.example.excelmaker.excelservice;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

public class Style {
    private static final String HEADER = "header";

    private final XSSFWorkbook workbook;

    private Style(XSSFWorkbook workbook) {
        this.workbook = workbook;
    }

    public static Style of(XSSFWorkbook workbook) {
        return new Style(workbook);
    }

    public CellStyle createHeaderStyle(CellStyle cellStyle, Field field) {
        for (Annotation annotation : field.getAnnotations()) {
            if (!(annotation instanceof HeaderStyle)) {
                continue;
            }

            HeaderStyle headerStyle = (HeaderStyle) annotation;
            setStyle(cellStyle, headerStyle.boarderStyle(), headerStyle.indexedColors(), headerStyle.fillPatternType(), headerStyle.horizontalAlignment(), headerStyle.verticalAlignment());

            break;
        }

        return cellStyle;
    }

    public CellStyle createBodyStyle(CellStyle cellStyle, Field field) {
        for (Annotation annotation : field.getAnnotations()) {
            if (!(annotation instanceof BodyStyle)) {
                continue;
            }

            BodyStyle bodyStyle = (BodyStyle) annotation;
            setStyle(cellStyle, bodyStyle.boarderStyle(), bodyStyle.indexedColors(), bodyStyle.fillPatternType(), bodyStyle.horizontalAlignment(), bodyStyle.verticalAlignment());

            break;
        }

        return cellStyle;
    }

    private void setStyle(CellStyle cellStyle, BorderStyle borderStyle, IndexedColors indexedColors, FillPatternType fillPatternType, HorizontalAlignment horizontalAlignment, VerticalAlignment verticalAlignment) {
        cellStyle.setBorderTop(borderStyle);
        cellStyle.setBorderBottom(borderStyle);
        cellStyle.setBorderLeft(borderStyle);
        cellStyle.setBorderRight(borderStyle);

        cellStyle.setFillForegroundColor(indexedColors.getIndex());
        cellStyle.setFillPattern(fillPatternType);

        cellStyle.setAlignment(horizontalAlignment);
        cellStyle.setVerticalAlignment(verticalAlignment);
    }
}
