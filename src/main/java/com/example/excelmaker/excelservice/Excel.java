package com.example.excelmaker.excelservice;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.lang.reflect.Field;
import java.util.List;

public class Excel<T> {

    private static final String EXCEL_CLASS_PATH = "com.example.excelmaker.excelform.";
    private static final String HEADER = "header";
    private static final String DATA = "data";

    private final XSSFWorkbook workbook;
    private final List<T> datas;
    private final Field[] fields;
    private final String sheetName;

    private Excel(XSSFWorkbook workbook, Field[] fields, List<T> datas, String sheetName) {
        this.workbook = workbook;
        this.datas = datas;
        this.fields = fields;
        this.sheetName = sheetName;
    }

    public static <T> Excel<T> of(XSSFWorkbook workbook, List<T> datas, String sheetName) {
        return new Excel<>(workbook, initDeclaredField(datas), datas, sheetName);
    }

    private static <T> Field[] initDeclaredField(List<T> datas) {
        if (datas.isEmpty()) {
            return new Field[0];
        }

        String simpleName = datas.get(0)
                .getClass()
                .getSimpleName();

        return getDeclaredFields(simpleName);
    }

    private static Field[] getDeclaredFields(String className) {
        try {
            Class<?> excelFormClass = Class.forName(EXCEL_CLASS_PATH + className);
            return excelFormClass.getDeclaredFields();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    public void generateSheet() {
        Sheet sheet = workbook.createSheet(sheetName);

        renderHeader(sheet);
        renderBody(sheet);
        adjustColumnSize(sheet);
    }

    private void renderHeader(Sheet sheet) {
        CellStyle headerStyle = workbook.createCellStyle();
        renderHeaderRow(sheet.createRow(0), headerStyle);
    }

    private void renderHeaderRow(Row row, CellStyle headerStyle) {
        for (int i = 0; i < fields.length; i++) {
            Style style = Style.of(workbook);
            headerStyle = style.createHeaderStyle(headerStyle, fields[i]);
            renderCell(fields[i].getName(), headerStyle, row.createCell(i));
        }
    }

    private void renderBody(Sheet sheet) {
        CellStyle dataStyle = workbook.createCellStyle();

        for (int i = 0; i < datas.size(); i++) {
            renderBodyRow(sheet.createRow(i + 1), datas.get(i), dataStyle);
        }
    }

    private void renderBodyRow(Row row, T data, CellStyle dataStyle) {
        for (int i = 0; i < fields.length; i++) {
            Field declaredField = fields[i];
            dataStyle = Style.of(workbook).createBodyStyle(dataStyle, declaredField);
            declaredField.setAccessible(true);

            Object value = getValue(data, declaredField);
            renderCell(value.toString(), dataStyle, row.createCell(i));
        }
    }

    private void renderCell(String data, CellStyle dataStyle, Cell cell) {
        cell.setCellValue(data);
        cell.setCellStyle(dataStyle);
    }

    private void adjustColumnSize(Sheet sheet) {
        for (int i = 0; i < fields.length; i++) {
            sheet.autoSizeColumn(i);
            sheet.setColumnWidth(i, sheet.getColumnWidth(i));
        }
    }

    private Object getValue(T data, Field declaredField) {
        try {
            return declaredField.get(data);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }
}
