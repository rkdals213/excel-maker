package com.example.excelmaker.excelservice;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.lang.reflect.Field;
import java.util.List;

public class ExcelService<T> {

    private static final String EXCEL_CLASS_PATH = "com.example.excelmaker.excelform.";
    private static final String HEADER = "header";
    private static final String DATA = "data";

    public XSSFWorkbook createExcel(XSSFWorkbook workbook, List<T> datas, String sheetName) {
        Sheet sheet = workbook.createSheet(sheetName);

        if (datas.isEmpty()) {
            return workbook;
        }

        Field[] declaredFields = getDeclaredFields(datas.get(0).getClass().getSimpleName());

        renderHeader(workbook, sheet, declaredFields);
        renderBody(workbook, sheet, datas, declaredFields);
        adjustColumnSize(sheet, declaredFields);

        return workbook;
    }

    private void renderHeader(XSSFWorkbook workbook, Sheet sheet, Field[] declaredFields) {
        CellStyle headerStyle = CellStyleSetting(workbook, HEADER);

        Row row = sheet.createRow(0);

        for (int i = 0; i < declaredFields.length; i++) {
            Cell cell = row.createCell(i);
            cell.setCellValue(declaredFields[i].getName());
            cell.setCellStyle(headerStyle);
        }
    }

    private void renderBody(XSSFWorkbook workbook, Sheet sheet, List<T> datas, Field[] declaredFields) {
        CellStyle dataStyle = CellStyleSetting(workbook, DATA);

        for (int i = 0; i < datas.size(); i++) {
            renderBody(sheet, datas.get(i), declaredFields, dataStyle, i + 1);
        }
    }

    private void renderBody(Sheet sheet, Object data, Field[] declaredFields, CellStyle dataStyle, int rowIndex) {
        Row row = sheet.createRow(rowIndex);

        for (int i = 0; i < declaredFields.length; i++) {
            Field declaredField = declaredFields[i];
            declaredField.setAccessible(true);

            Object value = getValue(data, declaredField);
            Cell cell = row.createCell(i);
            cell.setCellValue(value.toString());
            cell.setCellStyle(dataStyle);
        }
    }

    private void adjustColumnSize(Sheet sheet, Field[] declaredFields) {
        for (int i = 0; i < declaredFields.length; i++) {
            sheet.autoSizeColumn(i);
            sheet.setColumnWidth(i, sheet.getColumnWidth(i));
        }
    }

    private Object getValue(Object data, Field declaredField) {
        try {
            return declaredField.get(data);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    private Field[] getDeclaredFields(String className) {
        try {
            Class<?> excelFormClass = Class.forName(EXCEL_CLASS_PATH + className);
            return excelFormClass.getDeclaredFields();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    private CellStyle CellStyleSetting(XSSFWorkbook workbook, String kind) {
        //테이블 스타일
        CellStyle cellStyle = workbook.createCellStyle();

        //가는 경계선
        cellStyle.setBorderTop(BorderStyle.THIN);
        cellStyle.setBorderBottom(BorderStyle.THIN);
        cellStyle.setBorderLeft(BorderStyle.THIN);
        cellStyle.setBorderRight(BorderStyle.THIN);

        if (kind.equals(HEADER)) {
            //배경색 회색
            cellStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
            cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        }

        //데이터는 가운데 정렬
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER); //중앙 정렬

        //폰트 설정
        Font fontOfGothic = workbook.createFont();
        fontOfGothic.setFontName("맑은 고딕");
        cellStyle.setFont(fontOfGothic);

        return cellStyle;
    }
}
