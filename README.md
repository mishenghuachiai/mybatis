# Mybatis-plus
1. 如果实体类中的属性是多个单词组合，数据库中的字段一定要有下划线_
## 例如：实体类
    @Data
    @TableName("user")
    public class User {
    private Integer id;
    private String userName;
    private String passWord;
    private String realName;
    }
## 数据库表中的属性一定要有下划线1
    create table user
    (
    id int(32) auto_increment primary key,
    user_name varchar(32) not null,
    pass_word varchar(50) not null,
    real_name varchar(32) null
    );
第一次提交并push
#
