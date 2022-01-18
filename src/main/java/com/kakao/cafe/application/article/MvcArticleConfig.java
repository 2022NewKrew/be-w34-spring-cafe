package com.kakao.cafe.application.article;

import com.kakao.cafe.adapter.out.infra.persistence.article.ArticleAdapter;
import com.kakao.cafe.adapter.out.infra.persistence.article.ArticleRepository;
import com.kakao.cafe.adapter.out.infra.persistence.article.JdbcArticleRepository;
import com.kakao.cafe.application.article.port.in.GetArticleInfoUseCase;
import com.kakao.cafe.application.article.port.in.WriteArticleUseCase;
import com.kakao.cafe.application.article.port.out.GetArticleInfoPort;
import com.kakao.cafe.application.article.port.out.RegisterArticlePort;
import com.kakao.cafe.application.article.service.GetArticleInfoService;
import com.kakao.cafe.application.article.service.WriteArticleService;
import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MvcArticleConfig {

    public final DataSource dataSource;

    public MvcArticleConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    public ArticleRepository articleRepository() {
        return new JdbcArticleRepository(dataSource);
    }

    @Bean
    public RegisterArticlePort registerArticlePort() {
        return new ArticleAdapter(articleRepository());
    }

    @Bean
    public GetArticleInfoPort getArticleInfoPort() {
        return new ArticleAdapter(articleRepository());
    }

    @Bean
    public WriteArticleUseCase writeArticleUseCase() {
        return new WriteArticleService(registerArticlePort());
    }

    @Bean
    public GetArticleInfoUseCase getArticleInfoUseCase() {
        return new GetArticleInfoService(getArticleInfoPort());
    }
}
