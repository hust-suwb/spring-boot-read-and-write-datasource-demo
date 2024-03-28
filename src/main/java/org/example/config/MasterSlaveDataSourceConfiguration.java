package org.example.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.sql.DataSource;

import org.example.db.MasterSlaveDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
public class MasterSlaveDataSourceConfiguration {

    @Bean(name = "masterSlaveDataSource")
    public DataSource dataSource(MasterSlaveDataSourceProperties properties) {

        MasterSlaveDataSource dataSource = new MasterSlaveDataSource();

        // 主数据库
        dataSource.setDefaultTargetDataSource(new HikariDataSource(new HikariConfig(properties.master())));

        // 从数据库
        Map<Object, Object> slaveDataSource = new HashMap<>();

        // 从数据库 Key
        dataSource.setSlaveKeys(new ArrayList<>());

        for (Map.Entry<String,Properties> entry : properties.slave().entrySet()) {

            if (slaveDataSource.containsKey(entry.getKey())) {
                throw new IllegalArgumentException("存在同名的从数据库定义：" + entry.getKey());
            }

            slaveDataSource.put(entry.getKey(), new HikariDataSource(new HikariConfig(entry.getValue())));

            dataSource.getSlaveKeys().add(entry.getKey());
        }

        // 设置从库
        dataSource.setTargetDataSources(slaveDataSource);

        return dataSource;
    }
}