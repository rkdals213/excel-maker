package com.example.excelmaker;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExcelService {

    public XSSFWorkbook excelDownloadXSSF(List<StayCount> stayCounts, String kind) throws Exception {
        //엑셀 다운 시작
        XSSFWorkbook workbook = new XSSFWorkbook();
        //엑셀 시트명 생성
        Sheet sheet = workbook.createSheet(kind);
        //행,열
        Row row;
        Cell cell;
        //헤더명
        String[] headerKey = {"stay", "count"};

        //테이블 헤더 스타일 적용
        CellStyle headerStyle = CellStyleSetting(workbook, "header");
        //테이블 데이터 스타일 적용
        CellStyle dataStyle = CellStyleSetting(workbook, "data");

        row = sheet.createRow(0);
        for (int i = 0; i < headerKey.length; i++) {        //헤더 구성
            cell = row.createCell(i);
            cell.setCellValue(headerKey[i]);
            cell.setCellStyle(headerStyle);
        }

        for (int i = 0; i < stayCounts.size(); i++) {    //데이터 구성
            row = sheet.createRow(i + 1);
            int cellIdx = 0;

            StayCount stayCount = stayCounts.get(i);

            cell = row.createCell(cellIdx++);
            cell.setCellValue(stayCount.getStay());
            cell.setCellStyle(dataStyle);

            cell = row.createCell(cellIdx);
            cell.setCellValue(stayCount.getCount());
            cell.setCellStyle(dataStyle);
        }

        //셀 넓이 자동 조정
        for (int i = 0; i < headerKey.length; i++) {
            sheet.autoSizeColumn(i);
            sheet.setColumnWidth(i, sheet.getColumnWidth(i));
        }

        return workbook;
    }

    public CellStyle CellStyleSetting(XSSFWorkbook workbook, String kind) {
        //테이블 스타일
        CellStyle cellStyle = workbook.createCellStyle();

        //가는 경계선
        cellStyle.setBorderTop(BorderStyle.THIN);
        cellStyle.setBorderBottom(BorderStyle.THIN);
        cellStyle.setBorderLeft(BorderStyle.THIN);
        cellStyle.setBorderRight(BorderStyle.THIN);

        if(kind.equals("header")) {
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
