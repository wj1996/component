package com.wj.web.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;
@Data
@Builder
public class PageBean<T> {

    List<T> beanList;
    private int totalSize;
}
