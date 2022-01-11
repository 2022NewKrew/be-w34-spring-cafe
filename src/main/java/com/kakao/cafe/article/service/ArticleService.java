package com.kakao.cafe.article.service;

import com.kakao.cafe.article.model.Article;
import com.kakao.cafe.article.model.ArticleRequest;
import com.kakao.cafe.article.repository.ArticleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;

@Service
@AllArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;

    public void writeArticle(ArticleRequest articleRequest){
        SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd HH:mm");

        articleRepository.save(Article.builder()
                .id((long) articleRepository.getNumberOfArticles() + 1)
                .author(articleRequest.getAuthor())
                .title(articleRequest.getTitle())
                .contents(articleRequest.getContents())
                .uploadTime(date.format(new Timestamp(System.currentTimeMillis())))
                .build());
    }

    public List<Article> getAllArticles(){
        return articleRepository.findAll();
    }

    public Article getArticleById(Long id){
        return articleRepository.findOneById(id);
    }
}
