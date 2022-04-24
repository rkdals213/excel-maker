package com.example.excelmaker.excelform;

import lombok.Data;

@Data
public class DiscountTarget {
    private String ctrtId;
    private String startMonth;
    private String endMonth;
    private String amount;
}
