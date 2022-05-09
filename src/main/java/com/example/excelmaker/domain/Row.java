package com.example.excelmaker.domain;

import java.util.HashMap;
import java.util.Map;

public class Row {
    private final Map<String, String> cells = new HashMap<>();

    public void add(String key, String value) {
        cells.put(key, value);
    }

    public Map<String, String> getCells() {
        return cells;
    }
}
