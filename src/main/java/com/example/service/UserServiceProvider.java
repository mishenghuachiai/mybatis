package com.example.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "self-server",url = "${server.addr.conf}")
public interface UserServiceProvider {
    @GetMapping("/testBoot/getUser/{id}")
    String getUser(@PathVariable int id);
}
