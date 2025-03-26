package com.example.service;

import org.springframework.stereotype.Component;

@Component
public class AppleServiceImpl implements FruitService {


    @Override
    public boolean isEat() {
        System.out.println("isEat");
        return false;
    }

    @Override
    public boolean hasPeel() {
        System.out.println("hasPeel");
        return false;
    }
}
