package com.example.excelmaker.domain;

import com.example.excelmaker.excelform.properties.SheetName;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
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

            SheetName sheetName = data.stream().findFirst()
                    .orElseThrow(() -> {
                        throw new RuntimeException("데이터가 없습니다");
                    })
                    .getClass()
                    .getDeclaredAnnotation(SheetName.class);

            excelWriter.write(sheetName.sheetName());
        }

        saveFile();
    }

    @Override
    public List<Sheet> readExcel() {
        ExcelReader excelReader = ExcelReader.of(PATH + fileName + EXTENSION);
        return excelReader.read();
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
