package com.example.excelmaker.domain;

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

public class ExcelReader {
    private final List<List<Map<String, String>>> sheets = new ArrayList<>();

    private final String path;
    private int rowCount;
    private int columnCount;

    public ExcelReader(String path) {
        this.path = path;
    }

    public static ExcelReader of(String path) {
        return new ExcelReader(path);
    }

    public List<List<Map<String, String>>> extract() {
        XSSFWorkbook workbook = createWorkBook();
        int sheetSize = workbook.getNumberOfSheets();
        for (int i = 0; i < sheetSize; i++) {
            XSSFSheet sheet = getSheet(workbook, i);

            List<String> keys = new ArrayList<>();
            List<Map<String, String>> sheetData = new ArrayList<>();

            extractHeader(sheet, keys);
            extractBody(sheet, keys, sheetData);

            sheets.add(sheetData);
        }

        return sheets;
    }

    private XSSFSheet getSheet(XSSFWorkbook workbook, int number) {
        XSSFSheet sheet = workbook.getSheetAt(number);

        rowCount = sheet.getPhysicalNumberOfRows();
        return sheet;
    }

    private XSSFRow getRow(XSSFSheet sheet) {
        XSSFRow row = sheet.getRow(0);

        columnCount = row.getPhysicalNumberOfCells();
        return row;
    }

    private XSSFWorkbook createWorkBook(){
        try {
            FileInputStream file = new FileInputStream(path);
            return new XSSFWorkbook(file);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("파일이 존재하지 않습니다");
        }
    }

    private void extractHeader(XSSFSheet sheet, List<String> keys) {
        XSSFRow row = getRow(sheet);
        for (int i = 0; i <  columnCount; i++) {
            XSSFCell cell = row.getCell(i);

            if (cell == null) {
                continue;
            }

            String value = cell.getStringCellValue();
            keys.add(value);
        }
    }

    private void extractBody(XSSFSheet sheet, List<String> keys, List<Map<String, String>> sheetData) {
        for (int i = 1; i < rowCount; i++) {
            Map<String, String> rowData = new HashMap<>();
            XSSFRow row = sheet.getRow(i);

            if (row == null) {
                continue;
            }

            mapRowData(keys, rowData, row);

            sheetData.add(rowData);
        }
    }

    private void mapRowData(List<String> keys, Map<String, String> rowData, XSSFRow row) {
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
}
