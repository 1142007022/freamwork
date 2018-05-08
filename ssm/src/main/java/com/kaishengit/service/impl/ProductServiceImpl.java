package com.kaishengit.service.impl;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kaishengit.enitys.Product;
import com.kaishengit.enitys.ProductType;
import com.kaishengit.mapper.ProductMapper;
import com.kaishengit.mapper.ProductTypeMapper;
import com.kaishengit.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private ProductTypeMapper productTypeMapper;

    @Override
    public Product findById(Integer id) {
        return productMapper.findById(id);
    }

    @Override
    public PageInfo<Product> findByPage(Integer p) {
        PageHelper.startPage(p,15);
        System.out.println("p------"+p);
        List<Product> lists = productMapper.findByPage();
        System.out.println("长度：-----"+lists.size());
        return new PageInfo<>(lists);
    }

    @Override
    public void delById(Integer id) {
        productMapper.delById(id);
    }

    @Override
    public List<ProductType> findAllType() {
        return productTypeMapper.findAll();
    }

    @Override
    public void update(Product product) {
        productMapper.update(product);
    }

    @Override
    public void addProduct(Product product) {
        product.setCommentNum(Product.default_comment_num);
        productMapper.addProduct(product);
    }

    @Override
    public PageInfo<Product> findAllProductByPageNoAndQueryParam(Integer p, Map<String, Object> map) {
        PageHelper.startPage(p,15);
        List<Product> lists = productMapper.findAllProductByPageNoAndQueryParam(map);
       return new PageInfo<>(lists);
    }
}
