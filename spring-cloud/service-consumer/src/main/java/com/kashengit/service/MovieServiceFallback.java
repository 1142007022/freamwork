package com.kashengit.service;

import org.springframework.stereotype.Component;

@Component
public class MovieServiceFallback implements MovieService {
    @Override
    public String findById(Integer id) {
        return "null";
    }
}
