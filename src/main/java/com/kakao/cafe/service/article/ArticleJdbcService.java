package com.kakao.cafe.service.article;

import com.kakao.cafe.domain.Entity.Article;
import com.kakao.cafe.domain.Repository.article.ArticleJdbcRepository;
import com.kakao.cafe.dto.article.ReferArticleDto;
import com.kakao.cafe.dto.article.WriteArticleDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ArticleJdbcService {
    private ArticleJdbcRepository articleJdbcRepository;

    public void save(WriteArticleDto writeArticleDto) {
        this.articleJdbcRepository.save(writeArticleDto.toEntity(1));
    }

    public List<ReferArticleDto> findAllArticles() {
        List<ReferArticleDto> articleList = this.articleJdbcRepository.findAll().stream()
                .map(ReferArticleDto::new).collect(Collectors.toList());
        return articleList;
    }

    public ReferArticleDto findArticleById(int id) {
        Article article = this.articleJdbcRepository.findById(id);
        return new ReferArticleDto(article);
    }

}
