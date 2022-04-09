package com.example.excelmaker;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DataMapper {

    List<StayCount> selectStayCount();
}
