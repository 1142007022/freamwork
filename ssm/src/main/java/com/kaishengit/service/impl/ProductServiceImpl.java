package com.kaishengit.service.impl;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import com.kaishengit.enitys.Product;
import com.kaishengit.enitys.ProductType;
import com.kaishengit.mapper.ProductMapper;
import com.kaishengit.mapper.ProductTypeMapper;
import com.kaishengit.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.List;
import java.util.Map;

@Service
@CacheConfig(cacheNames = "products")
public class ProductServiceImpl implements ProductService{

    private Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

    /*@Autowired
    private JedisPool jedisPool;*/

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private ProductTypeMapper productTypeMapper;


    @Override
    @Cacheable
    public Product findById(Integer id) {
        /*  final String KEY = "product:"+id;
        Product product = null;
        Jedis jedis = jedisPool.getResource();
        //1.查询redis中是否存在该键
        if(jedis.exists(KEY)) {
            String json = jedis.get(KEY);
            product = new Gson().fromJson(json,Product.class);
            logger.info("find Product {} from Redis",id);
        } else {
            product = productMapper.findById(id);
            if(product != null) {
                String json = new Gson().toJson(product);
                jedis.set(KEY,json);
            }
            logger.info("find Product {} from MySQL",id);
        }
        jedis.close();*/
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
