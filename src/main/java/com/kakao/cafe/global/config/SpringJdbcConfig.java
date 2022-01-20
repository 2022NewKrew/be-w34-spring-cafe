package com.kakao.cafe.global.config;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.jdbc.datasource.init.ScriptUtils;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.beans.beancontext.BeanContext;
import java.sql.Connection;
import java.sql.SQLException;

@Configuration
public class SpringJdbcConfig {

    private final DataSource dataSource;

    public SpringJdbcConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @PostConstruct
    public void databaseInit() throws SQLException {
        Connection conn = dataSource.getConnection();
        ScriptUtils.executeSqlScript(conn, new ClassPathResource("sql/schema.sql"));
        ScriptUtils.executeSqlScript(conn, new ClassPathResource("sql/data.sql"));
    }
}
