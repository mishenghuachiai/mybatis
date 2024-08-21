package com.example.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author:0xOO
 * @Date: 2018/9/26 0026
 * @Time: 14:39
 */
@Data
@TableName("user")
public class User {
    private Integer id;
    private String userName;
    private String passWord;
    private String realName;
}
