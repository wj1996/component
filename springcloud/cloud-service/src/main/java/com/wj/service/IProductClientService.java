package com.wj.service;

import com.wj.config.FeignClientConfig;
import com.wj.vo.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@FeignClient(name = "CLOUD-PROVIDER",configuration = FeignClientConfig.class)
public interface IProductClientService {

    @RequestMapping("/product/get/{id}")
    public Product getProduct(@PathVariable("id") long id);

    @RequestMapping("/product/list")
    public List<Product> listProduct();

    @RequestMapping("/product/add")
    public boolean addProduct(Product product);
}
