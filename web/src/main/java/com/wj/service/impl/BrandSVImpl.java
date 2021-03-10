package com.wj.service.impl;

import com.wj.dao.IBrandDao;
import com.wj.service.IBrandSV;
import com.wj.utils.Constant;
import com.wj.web.model.Brand;
import com.wj.web.model.PageBean;
import com.wj.web.model.PageRequest;
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

    @Override
    public PageBean<Brand> getBrandList(PageRequest pageRequest) throws Exception {
        if (pageRequest == null) return null;
        Integer pageNum = pageRequest.getPageNum();
        Integer pageSize = pageRequest.getPageSize();
        if (pageNum == null || 0 == pageNum) {
            pageRequest.setPageNum(1);
        }
        if (pageSize == null || 0 == pageSize) {
            pageRequest.setPageSize(Constant.Page.DEFAULT_PAGE_SIZE);
        }
        return PageBean.<Brand>builder()
                .totalSize(brandDao.getTotalSize())
                .beanList(brandDao.getBrandList(pageRequest))
                .build();
    }
}
