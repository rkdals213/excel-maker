package com.example.excelmaker.domain;

import java.util.List;
import java.util.Map;

public interface Excel {

    void writeExcel();

    List<List<Map<String, String>>> readExcel();
}
