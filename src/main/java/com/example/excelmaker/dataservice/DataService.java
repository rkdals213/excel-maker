package com.example.excelmaker.dataservice;

import com.example.excelmaker.excelform.DeveloperInfo;
import com.example.excelmaker.excelform.ExerciseCount;
import com.example.excelmaker.excelservice.ExcelSheet;
import com.example.excelmaker.excelform.StayCount;
import lombok.AllArgsConstructor;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

@Service
@AllArgsConstructor
public class DataService {
    private static final String PATH = "/Users/kang/IdeaProjects/excel-maker/";
    private static final String EXTENSION = ".xlsx";

    private DataMapper dataMapper;

    public void createExcel() throws Exception {
        List<StayCount> stayCounts = dataMapper.selectStayCount();
        List<ExerciseCount> exerciseCounts = dataMapper.selectExerciseCount();
        List<DeveloperInfo> developerInfos = dataMapper.selectDeveloperInfo();

        XSSFWorkbook workbook = new XSSFWorkbook();

        ExcelSheet.of(workbook, stayCounts, "stayCounts")
                .generate();

        ExcelSheet.of(workbook, exerciseCounts, "exerciseCounts")
                .generate();

        ExcelSheet.of(workbook, developerInfos, "developerInfos")
                .generate();

        String fileName = PATH + "countData" + EXTENSION;

        saveFile(workbook, fileName);
    }

    private void saveFile(XSSFWorkbook excel, String fileName) throws IOException {
        File file = new File(fileName);
        FileOutputStream fos = new FileOutputStream(file);
        excel.write(fos);

        excel.close();
        fos.close();
    }
}
