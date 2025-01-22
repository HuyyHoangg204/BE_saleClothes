package com.sale_clothes.nhom11.controller;


import com.sale_clothes.nhom11.service.BaseRedisService;
import com.sale_clothes.nhom11.service.impl.BaseRedisServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/redis")
public class RedisController {
    @Autowired
    private BaseRedisServiceImpl<String, String, String> baseRedisService;

    @PostMapping
    private void setRedis() {
        baseRedisService.set("mrhoang", "deptrai");
    }
}
