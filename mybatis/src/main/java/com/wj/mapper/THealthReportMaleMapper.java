package com.wj.mapper;

import com.wj.model.THealthReportMale;

public interface THealthReportMaleMapper {

    THealthReportMale selectByUserId(Integer userId);
}