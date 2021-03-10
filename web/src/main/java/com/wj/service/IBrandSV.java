package com.wj.service;

import com.wj.web.model.Brand;
import com.wj.web.model.PageBean;
import com.wj.web.model.PageRequest;

public interface IBrandSV {

    void save(Brand brand) throws Exception;

    PageBean<Brand> getBrandList(PageRequest pageRequest) throws Exception;

}
