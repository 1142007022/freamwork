package com.kaishengit.mapper;

import com.kaishengit.enitys.Product;

import java.util.List;

public interface ProductMapper {

    Product findById(Integer id);

    List<Product> findAll();

    void deleteById(Integer id);

    void update(Product product);

}
