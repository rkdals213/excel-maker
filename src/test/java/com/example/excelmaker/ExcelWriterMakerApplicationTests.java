package com.example.excelmaker;

import com.example.excelmaker.dataservice.DataService;
import com.example.excelmaker.excelform.DeveloperInfo;
import com.example.excelmaker.excelform.StayCount;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.annotation.Annotation;
import java.util.Arrays;

@SpringBootTest
class ExcelWriterMakerApplicationTests {

    @Autowired
    DataService dataService;

    @Test
    void write() throws Exception {
        dataService.createExcel();
    }

    @Test
    void read() throws Exception {
        dataService.readExcel();
    }
}
