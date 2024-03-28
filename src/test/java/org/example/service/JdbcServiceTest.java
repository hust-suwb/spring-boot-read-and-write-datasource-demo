package org.example.service;

import org.example.DemoApplicationTests;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class JdbcServiceTest {

    private static final Logger log = LoggerFactory.getLogger(DemoApplicationTests.class);

    @Autowired
    private JdbcService jdbcService;

    @Test
    public void test() throws Exception {

        // 连续4次读
        log.info("read={}", this.jdbcService.select());
        log.info("read={}", this.jdbcService.select());
        log.info("read={}", this.jdbcService.selectByMaster());
        log.info("read={}", this.jdbcService.selectByMaster());

        // 写
        log.info("write={}", this.jdbcService.update());
    }
}
