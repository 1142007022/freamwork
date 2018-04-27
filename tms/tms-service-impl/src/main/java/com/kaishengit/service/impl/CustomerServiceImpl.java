package com.kaishengit.service.impl;

import com.kaishengit.entitys.Customer;
import com.kaishengit.mapper.CustomerMapper;
import com.kaishengit.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerMapper customerMapper;

    @Override
    public void add(Customer customer) {
        customerMapper.insertSelective(customer);
    }
}
