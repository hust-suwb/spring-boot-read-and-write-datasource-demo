package org.example;

import org.example.service.UserService;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class DemoApplicationTests {

    private static final Logger log = LoggerFactory.getLogger(DemoApplicationTests.class);

    @Autowired
    private UserService userService;

    @Test
    public void test() throws Exception {

    }
}