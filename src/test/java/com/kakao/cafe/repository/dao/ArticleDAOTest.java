package com.kakao.cafe.repository.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ArticleDAOTest {

    @Autowired
    ArticleDAO articleDAO;

    @Test
    void create() {
    }

    @Test
    void findById() {
    }

    @Test
    void findAll() {
        assertEquals(articleDAO.findAll().size(),2);
    }
}