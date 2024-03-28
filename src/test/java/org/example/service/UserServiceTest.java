package org.example.service;

import org.example.DemoApplicationTests;
import org.example.entity.User;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserServiceTest {

    private static final Logger log = LoggerFactory.getLogger(DemoApplicationTests.class);

    @Autowired
    private UserService userService;

    @Test
    public void test() throws Exception {

        // 读
        log.info("get={}", userService.getUser(1));
        log.info("getMaster={}", userService.getUserByMaster(1));

        // 写
        User user = new User();
        user.setId(1);
        user.setName(UUID.randomUUID().toString());
        log.info("update={}", userService.updateUser(user));

        // 读
        log.info("get={}", userService.getUser(1));
        log.info("getMaster={}", userService.getUserByMaster(1));
    }
}