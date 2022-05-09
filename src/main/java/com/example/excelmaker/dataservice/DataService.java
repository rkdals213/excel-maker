package com.example.excelmaker.dataservice;

import com.example.excelmaker.domain.DefaultExcel;
import com.example.excelmaker.domain.Excel;
import com.example.excelmaker.domain.Row;
import com.example.excelmaker.domain.Sheet;
import com.example.excelmaker.excelform.DeveloperInfo;
import com.example.excelmaker.excelform.ExerciseCount;
import com.example.excelmaker.excelform.StayCount;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class DataService {
    private static final String PATH = "/Users/kang/IdeaProjects/excel-maker/";
    private static final String EXTENSION = ".xlsx";

    private DataMapper dataMapper;

    public void createExcel() {
        List<StayCount> stayCounts = dataMapper.selectStayCount();
        List<ExerciseCount> exerciseCounts = dataMapper.selectExerciseCount();
        List<DeveloperInfo> developerInfos = dataMapper.selectDeveloperInfo();

        Excel excel = new DefaultExcel("test", stayCounts, exerciseCounts, developerInfos);
        excel.writeExcel();
    }

    public void readExcel() throws Exception{
        Excel excel = new DefaultExcel("test");
        List<Sheet> sheets = excel.readExcel();

        for (Sheet sheet : sheets) {
            for (Row dataLine : sheet.getRows()) {
                dataLine.getCells().forEach((key, value) -> System.out.println(key + " : " + value));
            }
            System.out.println();
            System.out.println("---------------------------------------------------------");
            System.out.println();
        }
    }

}
