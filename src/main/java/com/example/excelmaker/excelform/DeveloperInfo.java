package com.example.excelmaker.excelform;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class DeveloperInfo {
    private Long id;
    private String name;
    private String hobby;
    private String dev_type;
    private String years_coding;
}
