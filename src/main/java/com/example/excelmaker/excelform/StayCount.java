package com.example.excelmaker.excelform;

import com.example.excelmaker.excelform.properties.BodyStyle;
import com.example.excelmaker.excelform.properties.HeaderColumnName;
import com.example.excelmaker.excelform.properties.HeaderStyle;
import com.example.excelmaker.excelform.properties.SheetName;
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
@SheetName(sheetName = "stay count")
public class StayCount {
    @HeaderStyle(boarderStyle = BorderStyle.NONE)
    @BodyStyle(boarderStyle = BorderStyle.THICK, indexedColors = IndexedColors.AQUA)
    @HeaderColumnName(name = "입원일")
    private String stay;

    @HeaderStyle(boarderStyle = BorderStyle.THICK)
    @BodyStyle(boarderStyle = BorderStyle.THIN, indexedColors = IndexedColors.BROWN)
    @HeaderColumnName(name = "회원 수")
    private int count;
}
