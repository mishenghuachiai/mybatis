package com.example.service;

import org.springframework.stereotype.Component;

@Component
public class OrangeServiceImpl implements FruitService{
    @Override
    public boolean isEat() {
        System.out.println("iseat");
        return false;
    }

    @Override
    public boolean hasPeel() {
        System.out.println("orangehaspeel");
        return false;
    }
}
