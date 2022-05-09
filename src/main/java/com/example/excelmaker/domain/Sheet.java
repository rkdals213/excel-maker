package com.example.excelmaker.domain;

import java.util.ArrayList;
import java.util.List;

public class Sheet {
    private final List<Row> rows = new ArrayList<>();

    public void add(Row row) {
        rows.add(row);
    }

    public List<Row> getRows() {
        return rows;
    }
}
