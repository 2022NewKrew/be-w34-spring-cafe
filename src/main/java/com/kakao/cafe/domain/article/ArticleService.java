package com.kakao.cafe.domain.article;

import com.kakao.cafe.domain.article.dto.ArticleRowDataDto;
import com.kakao.cafe.domain.article.repository.ArticleRepository;
import com.kakao.cafe.domain.user.User;
import com.kakao.cafe.domain.user.repository.UserRepository;
import com.kakao.cafe.global.error.exception.NoPermissionException;
import com.kakao.cafe.global.error.exception.NoSuchArticleException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;


@Service
public class ArticleService {

    private final ArticleRepository articleRepository;
    private final UserRepository userRepository;

    Logger logger = LoggerFactory.getLogger(ArticleService.class);

    @Autowired
    public ArticleService(ArticleRepository articleRepository, UserRepository userRepository) {
        this.articleRepository = articleRepository;
        this.userRepository = userRepository;
    }

    // Article (질문하기) 등록
    public Article register(Article article) {
        Long writerId = getWriterLongIdFromUserId(article.getWriterUserId());
        ArticleRowDataDto articleRowDataDto = ArticleRowDataDto.from(article, writerId);
        article.setId(articleRepository.save(articleRowDataDto).getId());
        return article;
    }

    // ArticleRowDataDto 를 Article로 변환
    private Article articleOf(ArticleRowDataDto articleRowDataDto) {
        String writerUserId = getWriterUserIdFromLongID(articleRowDataDto.getWriterId());
        return Article.of(articleRowDataDto, writerUserId);
    }

    // param: userId(string)
    // return: user의 pk id(Long)
    private Long getWriterLongIdFromUserId(String userId){
        return userRepository.findByUserId(userId).orElseThrow(() -> {throw new NoSuchElementException("해당 아이디의 유저가 없습니다.");}).getId();
    }

    // param: user의 pk id(Long)
    // return: userId(string)
    private String getWriterUserIdFromLongID(Long id){
        return userRepository.findById(id).orElseThrow(() -> {throw new NoSuchElementException("해당 아이디의 유저가 없습니다.");}).getUserId();
    }


    public List<Article> getAllArticle(){
        return articleRepository.findAll().stream()
                .map(this::articleOf)
                .collect(Collectors.toList());
    }

    public Article getArticle(Long id){
        return articleOf(articleRepository.findById(id).orElseThrow(NoSuchArticleException::new));
    }

    public Article getArticle(Long id, User user) {
        Article findArticle = checkUserPermission(id, user);
        return findArticle;
    }

    private Article checkUserPermission(Long id, User user) {
        Article findArticle = articleOf(articleRepository.findById(id).orElseThrow(NoSuchArticleException::new));
        if (!user.getUserId().equals(findArticle.getWriterUserId())) { throw new NoPermissionException(); }
        return findArticle;
    }

    public Article updateArticle(Article updateArticle, User user) {
        checkUserPermission(updateArticle.getId(), user);
        ArticleRowDataDto articleRowDataDto = ArticleRowDataDto.from(updateArticle, user.getId());
        return articleOf(articleRepository.update(articleRowDataDto));
    }

    public boolean deleteArticle(Long id, User user) {
        Article findArticle = checkUserPermission(id, user);
        return articleRepository.deleteById(id);
    }
}
