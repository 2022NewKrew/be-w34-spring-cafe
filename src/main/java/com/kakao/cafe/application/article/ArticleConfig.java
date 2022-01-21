package com.kakao.cafe.application.article;

import com.kakao.cafe.adapter.out.infra.persistence.article.ArticleAdapter;
import com.kakao.cafe.adapter.out.infra.persistence.article.ArticleMapper;
import com.kakao.cafe.adapter.out.infra.persistence.article.ArticleRepository;
import com.kakao.cafe.adapter.out.infra.persistence.article.JdbcArticleRepository;
import com.kakao.cafe.adapter.out.infra.persistence.reply.ReplyRepository;
import com.kakao.cafe.application.article.port.in.DeleteArticleUseCase;
import com.kakao.cafe.application.article.port.in.GetArticleInfoUseCase;
import com.kakao.cafe.application.article.port.in.UpdateArticleUseCase;
import com.kakao.cafe.application.article.port.in.WriteArticleUseCase;
import com.kakao.cafe.application.article.port.out.DeleteArticlePort;
import com.kakao.cafe.application.article.port.out.GetArticleInfoPort;
import com.kakao.cafe.application.article.port.out.RegisterArticlePort;
import com.kakao.cafe.application.article.port.out.UpdateArticlePort;
import com.kakao.cafe.application.article.service.DeleteArticleService;
import com.kakao.cafe.application.article.service.GetArticleInfoService;
import com.kakao.cafe.application.article.service.UpdateArticleService;
import com.kakao.cafe.application.article.service.WriteArticleService;
import com.kakao.cafe.domain.article.Article;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.RowMapper;

@Configuration
public class ArticleConfig {

    public final DataSource dataSource;
    public final ReplyRepository replyRepository;

    @Autowired
    public ArticleConfig(DataSource dataSource, ReplyRepository replyRepository) {
        this.dataSource = dataSource;
        this.replyRepository = replyRepository;
    }

    @Bean
    public RowMapper<Article> articleMapper() {
        return new ArticleMapper();
    }

    @Bean
    public ArticleRepository articleRepository() {
        return new JdbcArticleRepository(dataSource, articleMapper());
    }

    @Bean
    public RegisterArticlePort registerArticlePort() {
        return new ArticleAdapter(articleRepository(), replyRepository);
    }

    @Bean
    public GetArticleInfoPort getArticleInfoPort() {
        return new ArticleAdapter(articleRepository(), replyRepository);
    }

    @Bean
    public UpdateArticlePort updateArticlePort() {
        return new ArticleAdapter(articleRepository(), replyRepository);
    }

    @Bean
    public DeleteArticlePort deleteArticlePort() {
        return new ArticleAdapter(articleRepository(), replyRepository);
    }

    @Bean
    public WriteArticleUseCase writeArticleUseCase() {
        return new WriteArticleService(registerArticlePort());
    }

    @Bean
    public GetArticleInfoUseCase getArticleInfoUseCase() {
        return new GetArticleInfoService(getArticleInfoPort());
    }

    @Bean
    public UpdateArticleUseCase updateArticleUseCase() {
        return new UpdateArticleService(updateArticlePort());
    }

    @Bean
    public DeleteArticleUseCase deleteArticleUseCase() {
        return new DeleteArticleService(deleteArticlePort(), getArticleInfoPort());
    }
}
