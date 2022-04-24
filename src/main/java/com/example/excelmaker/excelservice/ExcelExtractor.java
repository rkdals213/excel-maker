package com.example.excelmaker.excelservice;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExcelExtractor {
    private final List<Map<String, String>> mapList = new ArrayList<>();
    private final List<String> keys = new ArrayList<>();

    private final String path;
    private int rowCount;
    private int columnCount;

    public ExcelExtractor(String path) {
        this.path = path;
    }

    public void extract() throws IOException {
        XSSFWorkbook workbook = createWorkBook();

        XSSFSheet sheet = getSheet(workbook);

        XSSFRow row = getRow(sheet);

        extractHeader(row);

        extractBody(sheet);

        for (Map<String, String> map : mapList) {
            System.out.println(map);
        }
    }

    private XSSFSheet getSheet(XSSFWorkbook workbook) {
        XSSFSheet sheet = workbook.getSheetAt(0);

        rowCount = sheet.getPhysicalNumberOfRows();
        return sheet;
    }

    private XSSFRow getRow(XSSFSheet sheet) {
        XSSFRow row = sheet.getRow(0);

        columnCount = row.getPhysicalNumberOfCells();
        return row;
    }

    private XSSFWorkbook createWorkBook() throws IOException {
        FileInputStream file = new FileInputStream(path);
        return new XSSFWorkbook(file);
    }

    private void extractHeader(XSSFRow row) {
        for (int i = 0; i <  columnCount; i++) {
            XSSFCell cell = row.getCell(i);

            if (cell == null) {
                continue;
            }

            String value = cell.getStringCellValue();
            keys.add(value);
        }
    }

    private void extractBody(XSSFSheet sheet) {
        for (int i = 1; i < rowCount; i++) {
            Map<String, String> rowData = new HashMap<>();
            XSSFRow row = sheet.getRow(i);

            if (row == null) {
                continue;
            }

            mapRowData(rowData, row);

            mapList.add(rowData);
        }
    }

    private void mapRowData(Map<String, String> rowData, XSSFRow row) {
        for (int j = 0; j < columnCount; j++) {
            XSSFCell cell = row.getCell(j);

            if (cell == null) {
                continue;
            }

            String key = keys.get(j);
            String value = cell.getStringCellValue();

            rowData.put(key, value);
        }
    }

    public static void main(String[] args) throws IOException {
        ExcelExtractor excelExtractor = new ExcelExtractor("/Users/kang/IdeaProjects/excel-maker/discountTarget.xlsx");
        excelExtractor.extract();
    }
}
