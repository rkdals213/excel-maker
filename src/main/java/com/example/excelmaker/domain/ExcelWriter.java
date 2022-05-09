package com.example.excelmaker.domain;

import com.example.excelmaker.excelform.properties.HeaderColumnName;
import com.example.excelmaker.domain.style.BodyColumnStyles;
import com.example.excelmaker.domain.style.ColumnStyles;
import com.example.excelmaker.domain.style.HeaderColumnStyles;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.lang.reflect.Field;
import java.util.List;

public class ExcelWriter<T> {
    private static final String EXCEL_CLASS_PATH = "com.example.excelmaker.excelform.";
    private static final String HEADER = "header";
    private static final String DATA = "data";

    private final XSSFWorkbook workbook;
    private final List<T> datas;
    private final Field[] fields;
    private final ColumnStyles headerColumnStyles = new HeaderColumnStyles();
    private final ColumnStyles bodyColumnStyles = new BodyColumnStyles();

    private ExcelWriter(XSSFWorkbook workbook, Field[] fields, List<T> datas) {
        this.workbook = workbook;
        this.datas = datas;
        this.fields = fields;
    }

    public static <T> ExcelWriter<T> of(XSSFWorkbook workbook, List<T> datas) {
        return new ExcelWriter<>(workbook, initDeclaredField(datas), datas);
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

    public void write(String sheetName) {
        Sheet sheet = workbook.createSheet(sheetName);

        initStyle();
        renderHeader(sheet);
        renderBody(sheet);
        adjustColumnSize(sheet);
    }

    private void initStyle() {
        for (Field field : fields) {
            CellStyle headerStyle = new Style()
                    .createHeaderStyle(workbook.createCellStyle(), field);

            CellStyle bodyStyle = new Style()
                    .createBodyStyle(workbook.createCellStyle(), field);

            headerColumnStyles.addStyle(field.getName(), headerStyle);
            bodyColumnStyles.addStyle(field.getName(), bodyStyle);
        }
    }

    private void renderHeader(Sheet sheet) {
        Row row = sheet.createRow(0);
        for (int i = 0; i < fields.length; i++) {
            HeaderColumnName headerColumnName = fields[i].getDeclaredAnnotation(HeaderColumnName.class);
            String fieldName = fields[i].getName();
            renderCell(headerColumnName.name(), headerColumnStyles.getStyle(fieldName), row.createCell(i));
        }
    }

    private void renderBody(Sheet sheet) {
        for (int i = 0; i < datas.size(); i++) {
            renderBodyRow(sheet.createRow(i + 1), datas.get(i));
        }
    }

    private void renderBodyRow(Row row, T data) {
        for (int i = 0; i < fields.length; i++) {
            Field field = fields[i];
            field.setAccessible(true);

            Object value = getValue(data, field);
            renderCell(value.toString(), bodyColumnStyles.getStyle(field.getName()), row.createCell(i));
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
