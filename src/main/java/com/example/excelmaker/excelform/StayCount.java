package com.example.excelmaker.excelform;

import com.example.excelmaker.excelservice.BodyStyle;
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
public class StayCount {
    @HeaderStyle(boarderStyle = BorderStyle.NONE)
    @BodyStyle(boarderStyle = BorderStyle.THICK, indexedColors = IndexedColors.AQUA)
    private String stay;

    @HeaderStyle(boarderStyle = BorderStyle.THICK)
    @BodyStyle(boarderStyle = BorderStyle.THIN, indexedColors = IndexedColors.BROWN)
    private int count;
}
