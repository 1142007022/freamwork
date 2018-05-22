package com.kashengit.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "MOVIE-SERVICE-PROVIDER",fallback = MovieServiceFallback.class)
public interface MovieService {

    @GetMapping("/movie/{id}")
    String findById(@PathVariable(name = "id") Integer id);

}
