package com.example.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.entity.User;
import com.example.mapper.UserMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper,User> implements UserService{
    private Lock lock = new ReentrantLock(true);



    /**
     *
     * 前提是使用MySQL数据库的可重复读隔离机制。如果是高并发的情况下，假设真的就有多个线程同时调用该方法。那么当事务的开启与提交能完整的包裹在lock与unlock之间时，那么就不会出现超卖的问题。显然事务的开启一定是在lock之后的，故关键在于事务的提交是否一定在unlock之前。
     * 如果在unlock之后，那么是真的有可能发生超卖的：比如线程一执行该方法完毕但事务却还没提交时，线程二获得锁也开始执行该方法，在可重复读的隔离机制下，线程二是读不到线程一对库存的操作结果的，从而超卖。
     */
    @Transactional
    @Override
    public void lessInventory() {
        lock.lock();
        // 执行数据库操作——查询商品库存数量  com.example.service.UserServiceImpl.getInventoryNum

        int count = getInventoryNum();
        // 如果 库存数量 满足要求 执行数据库操作——减少库存数量——模拟卖出货物操作
        if(count>0){
            subInventory(count);
        }
        lock.unlock();
    }

    // 查询库存
    private int getInventoryNum(){
        String DRIVER="com.mysql.cj.jdbc.Driver";
        String DB_URL = "jdbc:mysql://localhost:3306/test?serverTimezone=GMT%2B8";
        String USER = "root";
        String PASS = "root";
        String QUERY = "SELECT sc.stock FROM sc WHERE id=1;";
        Connection conn = null;
        try {
            Class.forName(DRIVER);
            conn= DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stmt = conn.createStatement();
            ResultSet resultSet = stmt.executeQuery(QUERY);
            int stockCount = 0;
            while (resultSet.next()) {
                // Retrieve by column name
                System.out.print(", name: " + resultSet.getInt("stock"));
                stockCount=  resultSet.getInt("stock");
            }
            return stockCount;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return 0;
    }
    //减少库存
    private String subInventory(int count){
        int setVal= count-1;
        String DRIVER="com.mysql.cj.jdbc.Driver";
        String DB_URL = "jdbc:mysql://localhost:3306/test?serverTimezone=GMT%2B8";
        String USER = "root";
        String PASS = "root";
        String QUERY = "UPDATE sc SET sc.`stock`="+setVal+" WHERE id=1;";
        Connection conn = null;
        try {
            Class.forName(DRIVER);
            conn= DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stmt = conn.createStatement();
            int num = stmt.executeUpdate(QUERY);
            if(num>0){
                return "success";
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

}
