package org.example.service;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class JdbcService {

    private final JdbcTemplate jdbcTemplate;

    public JdbcService(JdbcTemplate jdbcTemplate) {
        super();
        this.jdbcTemplate = jdbcTemplate;
    }

    // 只读
    @Transactional(readOnly = true)
    public String read () {
        return this.jdbcTemplate.queryForObject("SELECT `name` FROM `test` WHERE id = 1;", String.class);
    }


    // 先写，再读
    @Transactional
    public String write () {
        this.jdbcTemplate.update("UPDATE `test` SET `name` = ? WHERE id = 1;", "new name");
        return this.read();
    }
}