package com.hyf.shardingsphere;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.transaction.jta.JtaAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import tk.mybatis.spring.annotation.MapperScan;

// shardingshpere分布式事务
@SpringBootApplication(exclude = {JtaAutoConfiguration.class})
@ComponentScan("com.hyf")
@MapperScan(basePackages = "com.hyf.shardingsphere.mapper")
public class ShardingsphereApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShardingsphereApplication.class, args);
    }

}

