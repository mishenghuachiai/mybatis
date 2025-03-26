package com.example.controller;

import com.example.service.FruitService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor(onConstructor_={@Autowired})
public class HelloController {


    private final List<FruitService> fruitServiceList;

    public  String test(){
        fruitServiceList.stream().forEach(System.out::println);
        return "hello";
    }
}
