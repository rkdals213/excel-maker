package com.example.excelmaker.domain;

import java.util.List;

public interface Excel {

    void writeExcel();

    List<Sheet> readExcel();
}
