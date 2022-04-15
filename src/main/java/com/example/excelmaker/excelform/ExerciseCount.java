package com.example.excelmaker.excelform;

import com.example.excelmaker.excelservice.BodyStyle;
import com.example.excelmaker.excelservice.HeaderColumnName;
import com.example.excelmaker.excelservice.HeaderStyle;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.IndexedColors;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class ExerciseCount {
    @HeaderStyle(boarderStyle = BorderStyle.NONE)
    @BodyStyle(boarderStyle = BorderStyle.THICK, indexedColors = IndexedColors.AQUA)
    @HeaderColumnName(name = "운동 주기")
    private String exercise;

    @HeaderStyle(boarderStyle = BorderStyle.THICK)
    @BodyStyle(boarderStyle = BorderStyle.THIN, indexedColors = IndexedColors.BROWN)
    @HeaderColumnName(name = "사람수")
    private int count;
}
