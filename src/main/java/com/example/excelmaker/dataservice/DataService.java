package com.example.excelmaker.dataservice;

import com.example.excelmaker.excelform.ExerciseCount;
import com.example.excelmaker.excelservice.ExcelService;
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

    public void createStayCountExcel() throws Exception {
        List<StayCount> stayCounts = dataMapper.selectStayCount();
        List<ExerciseCount> exerciseCounts = dataMapper.selectExerciseCount();

        XSSFWorkbook workbook = new XSSFWorkbook();

        ExcelService<StayCount> stayCountExcelService = new ExcelService<>();
        XSSFWorkbook excel = stayCountExcelService.createExcel(workbook, stayCounts, "stayCounts");

        ExcelService<ExerciseCount> exerciseCountExcelService = new ExcelService<>();
        exerciseCountExcelService.createExcel(workbook, exerciseCounts, "exerciseCounts");

        String fileName = PATH + "countData" + EXTENSION;

        saveFile(excel, fileName);
    }

    private void saveFile(XSSFWorkbook excel, String fileName) throws IOException {
        File file = new File(fileName);
        FileOutputStream fos = new FileOutputStream(file);
        excel.write(fos);

        excel.close();
        fos.close();
    }


}
