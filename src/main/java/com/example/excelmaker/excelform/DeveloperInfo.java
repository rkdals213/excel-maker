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
public class DeveloperInfo {
    @HeaderStyle(boarderStyle = BorderStyle.NONE)
    @BodyStyle(boarderStyle = BorderStyle.THICK, indexedColors = IndexedColors.AQUA)
    @HeaderColumnName(name = "아이디")
    private Long id;

    @HeaderStyle(boarderStyle = BorderStyle.NONE)
    @BodyStyle(boarderStyle = BorderStyle.THICK, indexedColors = IndexedColors.AQUA)
    @HeaderColumnName(name = "병원 이름")
    private String name;

    @HeaderStyle(boarderStyle = BorderStyle.THICK)
    @BodyStyle(boarderStyle = BorderStyle.THIN, indexedColors = IndexedColors.BROWN)
    @HeaderColumnName(name = "취미존재")
    private String hobby;

    @HeaderStyle(boarderStyle = BorderStyle.THICK)
    @BodyStyle(boarderStyle = BorderStyle.THIN, indexedColors = IndexedColors.BROWN)
    @HeaderColumnName(name = "기술 스택")
    private String dev_type;

    @HeaderStyle(boarderStyle = BorderStyle.THICK)
    @BodyStyle(boarderStyle = BorderStyle.THIN, indexedColors = IndexedColors.BROWN)
    @HeaderColumnName(name = "년차")
    private String years_coding;
}
