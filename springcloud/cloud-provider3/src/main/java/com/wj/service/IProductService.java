package com.wj.service;
import com.wj.vo.Product;

import java.util.List;
public interface IProductService {
    Product get(long id);
    boolean add(Product product);
    List<Product> list();
}