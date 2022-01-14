package com.kakao.cafe.application.article;

import com.kakao.cafe.adapter.out.infra.persistence.article.ArticleInfoRepository;
import com.kakao.cafe.adapter.out.infra.persistence.article.InMemoryArticleInfoRepository;
import com.kakao.cafe.adapter.out.infra.persistence.article.StoreArticleInfoAdapter;
import com.kakao.cafe.application.article.port.in.GetArticleInfoUseCase;
import com.kakao.cafe.application.article.port.in.WriteArticleUseCase;
import com.kakao.cafe.application.article.port.out.GetArticleInfoPort;
import com.kakao.cafe.application.article.port.out.RegisterArticlePort;
import com.kakao.cafe.application.article.service.GetArticleInfoService;
import com.kakao.cafe.application.article.service.WriteArticleService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MvcArticleConfig {

    @Bean
    public ArticleInfoRepository articleInfoRepository() {
        return new InMemoryArticleInfoRepository();
    }

    @Bean
    public RegisterArticlePort registerArticlePort() {
        return new StoreArticleInfoAdapter(articleInfoRepository());
    }

    @Bean
    public GetArticleInfoPort getArticleInfoPort() {
        return new StoreArticleInfoAdapter(articleInfoRepository());
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
