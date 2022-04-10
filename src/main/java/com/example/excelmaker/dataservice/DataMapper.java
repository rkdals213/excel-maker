package com.example.excelmaker.dataservice;

import com.example.excelmaker.excelform.ExerciseCount;
import com.example.excelmaker.excelform.StayCount;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DataMapper {

    List<StayCount> selectStayCount();

    List<ExerciseCount> selectExerciseCount();
}
