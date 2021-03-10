package com.wj.service.impl;

import com.wj.dao.IBrandDao;
import com.wj.service.IBrandSV;
import com.wj.web.model.Brand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BrandSVImpl implements IBrandSV {

    @Autowired
    private IBrandDao brandDao;

    @Override
    public void save(Brand brand) throws Exception {
        brandDao.save(brand);
    }
}
