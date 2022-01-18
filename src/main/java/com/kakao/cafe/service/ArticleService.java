package com.kakao.cafe.service;

import com.kakao.cafe.domain.Article;
import com.kakao.cafe.domain.User;
import com.kakao.cafe.dto.ArticleFormRequest;
import com.kakao.cafe.mapper.ArticleMapper;
import com.kakao.cafe.mapper.UserMapper;
import com.kakao.cafe.repository.ArticleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.security.sasl.AuthenticationException;
import java.util.List;

@Transactional
@Service
public class ArticleService {
    private final ArticleRepository articleRepository;

    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }


    public void save(ArticleFormRequest articleFormRequest, String writer) {
        Article article = ArticleMapper.INSTANCE.toEntity(articleFormRequest);
        article.setWriter(writer);
        articleRepository.save(article);
    }

    public Article findArticle(Long id) {
        return articleRepository.findById(id).
                orElseThrow(() -> new IllegalArgumentException("해당 id에 맞는 article이 존재하지 않습니다."));
    }

    public List<Article> findArticleList() {
        return articleRepository.findAll();
    }

    public void updateArticleInfo(Long id, ArticleFormRequest articleFormRequest) {
        Article updateArticle = ArticleMapper.INSTANCE.toEntity(articleFormRequest);

        articleRepository.updateArticle(id, updateArticle);
    }


}
