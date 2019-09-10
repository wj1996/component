package com.wj.controller;

import com.wj.service.IProductClientService;
import com.wj.vo.Product;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 使用Feign来简化
 */
@RestController
@RequestMapping("/consumer2")
public class ConsumerProductController {

    @Resource
    private IProductClientService iProductClientService;


    @RequestMapping("/product/get")
    public Object getProduct(long id) {
        return iProductClientService.getProduct(id);
    }

    @RequestMapping("/product/list")
    public  Object listProduct() {
        return iProductClientService.listProduct();
    }

    @RequestMapping("/product/add")
    public Object addPorduct(Product product) {
        return iProductClientService.addProduct(product);
    }
}