package com.example.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.example.entity.User;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Wrapper;
import java.util.Optional;

/**
 * @Author:0xOO
 * @Date: 2018/9/26 0026
 * @Time: 14:42
 */

@RestController
@RequestMapping("/testBoot")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("getUser/{id}")
    public String GetUser(@PathVariable int id){
       return id<=0||id>5?"none":userService.getOne(Wrappers.<User>lambdaQuery().eq(User::getId, id)).toString();
    }
}
