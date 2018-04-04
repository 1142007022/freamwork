package com.kaishengit.service;

import com.kaishengit.enitys.Product;
import com.kaishengit.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductMapper productMapper;

    public Product findById(Integer id){
        return productMapper.findById(id);
    }

    public List<Product> findAll(){
        return productMapper.findAll();
    }

    public void deleteById(Integer id) {
        productMapper.deleteById(id);
    }

    public void update(Product product){
        productMapper.update(product);
    }
}
