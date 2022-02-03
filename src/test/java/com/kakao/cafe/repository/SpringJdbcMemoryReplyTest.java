package com.kakao.cafe.repository;

import com.kakao.cafe.SpringJdbcConfig;
import com.kakao.cafe.domain.Reply;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.event.annotation.BeforeTestClass;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class SpringJdbcMemoryReplyTest {

    private final static Logger logger = LoggerFactory.getLogger(SpringJdbcMemoryReplyTest.class);

    @Test
    void save() {
        logger.info("replyRepository test - save");
        ApplicationContext ac = new AnnotationConfigApplicationContext(SpringJdbcConfig.class);
        ReplyRepository replyRepository = ac.getBean("replyRepository", ReplyRepository.class);
//        replyRepository.save(new Reply("test", "testContent"));

    }
}
