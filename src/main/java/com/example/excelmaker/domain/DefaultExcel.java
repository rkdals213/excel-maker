package com.example.excelmaker.domain;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DefaultExcel<T> implements Excel {
    private static final String PATH = "/Users/kang/IdeaProjects/excel-maker/";
    private static final String EXTENSION = ".xlsx";

    private final XSSFWorkbook workbook;
    private final String fileName;
    private final List<List<T>> datas;

    @SafeVarargs
    public DefaultExcel(String fileName, List<T>... datas) {
        this.workbook = new XSSFWorkbook();
        this.fileName = fileName;
        this.datas = Arrays.stream(datas)
                .collect(Collectors.toList());
    }

    @Override
    public void writeExcel() {
        if (datas.isEmpty()) {
            throw new RuntimeException("데이터를 입력하세요");
        }

        for (List<T> data : datas) {
            ExcelWriter<T> excelWriter = ExcelWriter.of(workbook, data);
            excelWriter.write();
        }

        saveFile();
    }

    @Override
    public List<List<Map<String, String>>> readExcel() {
        ExcelReader excelReader = ExcelReader.of(PATH + fileName + EXTENSION);
        return excelReader.extract();
    }

    private void saveFile() {
        File file = new File(PATH + fileName + EXTENSION);
        try {
            FileOutputStream fos = new FileOutputStream(file);
            workbook.write(fos);
            workbook.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
