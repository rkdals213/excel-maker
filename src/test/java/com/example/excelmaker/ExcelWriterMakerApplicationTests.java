package com.example.excelmaker;

import com.example.excelmaker.dataservice.DataService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
