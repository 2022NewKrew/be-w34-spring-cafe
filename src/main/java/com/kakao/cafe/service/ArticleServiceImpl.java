package com.kakao.cafe.service;

import com.kakao.cafe.domain.Article;
import com.kakao.cafe.domain.Reply;
import com.kakao.cafe.dto.ArticleDto;
import com.kakao.cafe.dto.ArticlePostDto;
import com.kakao.cafe.dto.ReplyContentsDto;
import com.kakao.cafe.dto.ReplyDto;
import com.kakao.cafe.repository.ArticleRepository;
import com.kakao.cafe.repository.ReplyRepository;
import com.kakao.cafe.repository.UserRepository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class ArticleServiceImpl implements ArticleService {
    private final ArticleRepository articleRepository;
    private final UserRepository userRepository;

    public ArticleServiceImpl(ArticleRepository articleRepository, UserRepository userRepository) {
        this.articleRepository = articleRepository;
        this.userRepository = userRepository;
    }

    public void post(ArticlePostDto article) throws SQLException, NoSuchElementException {
        userRepository.findByUserId(article.getWriter());

        articleRepository.save(article.toEntity());
    }

    public void update(int id, ArticlePostDto modifiedArticle) throws NoSuchElementException {
        Article modifiedArticleEntity = articleRepository.findById(id);

        modifiedArticleEntity.setTitle(modifiedArticle.getTitle());
        modifiedArticleEntity.setContents(modifiedArticle.getContents());

        articleRepository.update(modifiedArticleEntity);
    }

    public void delete(int id) throws NoSuchElementException {
        articleRepository.findById(id); // id값을 갖는 article이 존재하는지 확인

        articleRepository.delete(id);
    }

    public List<ArticleDto> getArticleList() {
        List<ArticleDto> articleDtoList = new ArrayList<>();

        for (Article article : articleRepository.findAll()) {
            articleDtoList.add(new ArticleDto(article.getId(), article.getWriter(), article.getTitle(), article.getContents()));
        }

        return articleDtoList;
    }

    public ArticleDto findById(int id) throws NoSuchElementException {
        Article article = articleRepository.findById(id);

        return new ArticleDto(
                article.getId(),
                article.getWriter(),
                article.getTitle(),
                article.getContents()
        );
    }
}
