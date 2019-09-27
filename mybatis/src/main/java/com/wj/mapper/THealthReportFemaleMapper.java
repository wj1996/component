package com.wj.mapper;

import com.wj.model.THealthReportFemale;

public interface THealthReportFemaleMapper {


    THealthReportFemale selectByUserId(Integer userId);
}