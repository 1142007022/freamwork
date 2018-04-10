package com.kaishengit.service.impl;


import com.kaishengit.enitys.Product;
import com.kaishengit.mapper.ProductMapper;
import com.kaishengit.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductMapper productMapper;


    @Override
    public Product findById(Integer id) {
        return productMapper.findById(id);
    }
}
