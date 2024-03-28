package org.example;

import org.example.config.MasterSlaveDataSourceProperties;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @see: https://springdoc.cn/spring-boot-read-and-write-datasource/
 * @see: https://www.cnblogs.com/cjsblog/p/9712457.html
 */
@SpringBootApplication
@EnableAspectJAutoProxy
@EnableConfigurationProperties(value = {MasterSlaveDataSourceProperties.class})
@MapperScan("org.example.mapper")
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
}