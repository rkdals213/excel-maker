package com.example.excelmaker;

import lombok.AllArgsConstructor;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

@Service
@AllArgsConstructor
public class DataService {
    private DataMapper dataMapper;

    private ExcelService excelService;

    public void createStayCountExcel() throws Exception {
        List<StayCount> stayCounts = dataMapper.selectStayCount();
        XSSFWorkbook excel = excelService.excelDownloadXSSF(stayCounts, "stayCounts");

        String localFile = "/Users/kang/IdeaProjects/excel-maker/" + "StayCount" + ".xlsx";

        File file = new File(localFile);
        FileOutputStream fos = new FileOutputStream(file);
        excel.write(fos);

        excel.close();
        fos.close();
    }
}
