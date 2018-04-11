package com.kaishengit.mapper;


import com.github.pagehelper.PageInfo;
import com.kaishengit.enitys.Product;

import java.util.List;
import java.util.Map;

public interface ProductMapper {

    public Product findById(Integer id);
    public List<Product> findByPage();
    public void delById(Integer id);
    void update(Product product);
    void addProduct(Product product);

    List<Product> findAllProductByPageNoAndQueryParam(Map<String, Object> map);
}
