package com.example.controller;

import com.example.service.ScService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
public class ScController {

    @Value("${http_url}")
    private String httpUrl;
    @Value("${name}")
    private String name;
    @Autowired
    ScService scService;

    @RequestMapping("/home")
    public String test() {
        System.out.println(httpUrl);
        System.out.println(name);
//        scService.test();
        return null;
    }
}
