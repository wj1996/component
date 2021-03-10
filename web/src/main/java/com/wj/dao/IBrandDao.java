package com.wj.dao;

import com.wj.web.model.Brand;
import com.wj.web.model.PageBean;
import com.wj.web.model.PageRequest;

import java.util.List;

public interface IBrandDao {

    void save(Brand brand) throws Exception;

    List<Brand> getBrandList(PageRequest pageRequest) throws Exception;

    int getTotalSize() throws Exception;
}
