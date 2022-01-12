package com.kakao.cafe.service;

import com.kakao.cafe.domain.Article;
import com.kakao.cafe.domain.Articles;
import com.kakao.cafe.domain.User;
import com.kakao.cafe.domain.Users;
import com.kakao.cafe.dto.ArticleDto;
import com.kakao.cafe.dto.UserDto;

import java.util.List;

public class ArticleService {
    private final Articles articles = new Articles();

    public void addPost(ArticleDto articleDto){
        articles.addPost(articleDto);
    }

    public List<Article> findAll(){
        return articles.getArticles();
    }

    public Article findById(int id){
        return articles.findById(id);
    }

}
