package org.example.service;

import org.example.annotation.Master;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class JdbcService {

    private final JdbcTemplate jdbcTemplate;

    public JdbcService(JdbcTemplate jdbcTemplate) {
        super();
        this.jdbcTemplate = jdbcTemplate;
    }

    // 只读
    public String select () {
        return this.jdbcTemplate.queryForObject("SELECT `name` FROM `test` WHERE id = 1;", String.class);
    }


    // 写
    public int update () {
        return this.jdbcTemplate.update("UPDATE `test` SET `name` = ? WHERE id = 1;", "new name");
    }

    // 主库 读
    @Master
    public String selectByMaster() {
        return this.jdbcTemplate.queryForObject("SELECT `name` FROM `test` WHERE id = 1;", String.class);
    }
}