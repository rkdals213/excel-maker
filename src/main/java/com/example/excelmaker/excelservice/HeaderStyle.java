package com.example.excelmaker.excelservice;

import org.apache.poi.ss.usermodel.*;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface HeaderStyle {
    BorderStyle boarderStyle() default BorderStyle.THIN;
    IndexedColors indexedColors() default IndexedColors.GREY_25_PERCENT;
    FillPatternType fillPatternType() default FillPatternType.SOLID_FOREGROUND;
    HorizontalAlignment horizontalAlignment() default HorizontalAlignment.CENTER;
    VerticalAlignment verticalAlignment() default VerticalAlignment.CENTER;
}
