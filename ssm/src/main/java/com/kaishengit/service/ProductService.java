package com.kaishengit.service;


import com.github.pagehelper.PageInfo;
import com.kaishengit.enitys.Product;
import com.kaishengit.enitys.ProductType;

import java.util.List;
import java.util.Map;

public interface ProductService {

    public Product findById(Integer id);
    public PageInfo<Product> findByPage(Integer p);
    public void delById(Integer id);
    List<ProductType> findAllType();
    void update(Product product);
    void addProduct(Product product);

    PageInfo<Product> findAllProductByPageNoAndQueryParam(Integer p, Map<String, Object> map);
}
