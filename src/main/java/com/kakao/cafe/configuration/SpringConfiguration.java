package com.kakao.cafe.configuration;

import com.kakao.cafe.repository.JdbcTemplatesArticle;
import com.kakao.cafe.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class SpringConfiguration {
    private DataSource dataSource;

    @Autowired
    public SpringConfiguration(DataSource dataSource){
        this.dataSource = dataSource;
    }

    @Bean
    public ArticleService articleService(){
        return new ArticleService(new JdbcTemplatesArticle(dataSource));
    }

}
