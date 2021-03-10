package com.wj.web.controller;

import com.wj.service.IBrandSV;
import com.wj.utils.Constant;
import com.wj.web.model.Brand;
import com.wj.web.model.CommonMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@RestController
public class BrandController {

    @Autowired
    private IBrandSV brandSV;

    @RequestMapping("getBrandList")
    public List<Brand> getList() {
        List list = new ArrayList();
        list.add(Brand.builder().id(1).name("test").ctime(new Date(System.currentTimeMillis())).build());
        list.add(Brand.builder().id(1).name("test").ctime(new Date(System.currentTimeMillis())).build());
        return list;
    }
    @RequestMapping("addBrand")
    public CommonMsg addBrand(Brand brand) {
        CommonMsg commonMsg = CommonMsg.builder().message(Constant.OK_STATUS).build();
        try {
            brandSV.save(brand);
        } catch (Exception e) {
            commonMsg.setStatus(Constant.NOT_OK_STATUS);
            commonMsg.setMessage(e.getMessage());
        }
        return commonMsg;
    }
}
