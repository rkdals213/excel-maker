package com.example.excelmaker;

import com.example.excelmaker.dataservice.DataService;
import com.example.excelmaker.excelform.StayCount;
import com.example.excelmaker.excelservice.HeaderStyle;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedType;
import java.lang.reflect.Field;
import java.util.Arrays;

@SpringBootTest
class ExcelSheetMakerApplicationTests {

    @Autowired
    DataService dataService;

    @Test
    void contextLoads() throws Exception {
        dataService.createExcel();
    }

    @Test
    void test() {
        StayCount stayCount = new StayCount("", 10);
        Field[] declaredFields = stayCount.getClass().getDeclaredFields();
        for (Field declaredField : declaredFields) {
            for (Annotation annotation : declaredField.getAnnotations()) {
                System.out.println(annotation);
                HeaderStyle headerStyle = (HeaderStyle) annotation;
                System.out.println(headerStyle.boarderStyle());

            }

        }

    }

}
