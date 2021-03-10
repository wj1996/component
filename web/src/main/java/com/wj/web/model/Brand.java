package com.wj.web.model;

import lombok.Builder;
import lombok.Data;

import java.sql.Date;

@Data
@Builder
public class Brand {

    private int id;
    private String name;
    private Date ctime;
}
