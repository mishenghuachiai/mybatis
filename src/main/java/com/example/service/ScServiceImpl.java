package com.example.service;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.entity.SC;
import com.example.mapper.ScMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class ScServiceImpl extends ServiceImpl<ScMapper, SC> implements ScService{
    @Autowired
    ScMapper scMapper;

    @Override
    @Transactional
    public void test() {
        test1();
    }

    public void test1() {
        Integer id = 1;
        LambdaUpdateWrapper<SC> lambdaUpdateWrapper = Wrappers.lambdaUpdate();
        lambdaUpdateWrapper.eq(SC::getId, id).set(SC::getPayTime, new Date());
        int updateNum = scMapper.update(null, lambdaUpdateWrapper);
        if (id > 0) {
            throw new RuntimeException("id不能大于1");
        }
        System.out.println(updateNum);
    }
}
