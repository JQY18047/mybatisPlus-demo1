package com.itheima.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.*;

//lombok

@TableName("tb_user2")
@Data  //@Data ： 注解在类上，提供类的get、set、equals、hashCode、canEqual、toString、无参构造方法， 没有有参构造方法。
public class User {
    @TableId(type = IdType.AUTO)
    private Long id;
    private String name;
    @TableField(value = "password", select = false)
    private String password;
    private Integer age;
    private String tel;
    @TableField(exist = false)
    private Integer online;
    //逻辑删除
    //    @TableLogic(value = "0",delval = "1")
    //    这个会是每次查询都带一个条件 where deleted=0
    //当执行删除操作时 会执行更新操作
    private Integer deleted;
    //乐观锁
    @Version
    private Integer version;
}
