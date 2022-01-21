package com.kakao.cafe.domain.article;

import com.kakao.cafe.domain.article.dto.ArticleAndCommentRawDataDto;
import com.kakao.cafe.domain.article.dto.ArticleRowDataDto;
import com.kakao.cafe.domain.article.repository.JdbcTemplateArticleRepository;
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

    private final JdbcTemplateArticleRepository articleRepository;
    private final UserRepository userRepository;

    Logger logger = LoggerFactory.getLogger(ArticleService.class);

    @Autowired
    public ArticleService(JdbcTemplateArticleRepository articleRepository, UserRepository userRepository) {
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

    // param: userId(string)
    // return: user의 pk id(Long)
    private Long getWriterLongIdFromUserId(String userId){
        return userRepository.findByUserId(userId).orElseThrow(() -> {throw new NoSuchElementException("해당 아이디의 유저가 없습니다.");}).getId();
    }

    // param: user의 pk id(Long)
    // return: userId(string)
    private String getWriterUserIdFromLongID(Long userLongid){
        return userRepository.findById(userLongid).orElseThrow(() -> {throw new NoSuchElementException("해당 아이디의 유저가 없습니다.");}).getUserId();
    }

    public List<Article> getAllArticle(){
        return articleRepository.findAll().stream()
                .map(Article::of)
                .collect(Collectors.toList());
    }

    public Article getArticle(Long articleId){
        return Article.of(articleRepository.findById(articleId).orElseThrow(NoSuchArticleException::new));
    }

    public Article getArticle(Long articleId, User user) {
        Article findArticle = checkUserPermission(articleId, user);
        return findArticle;
    }

    // Article id 와 session의 user로 article의 작성자인지 확인 후 Article 객체를 반환
    private Article checkUserPermission(Long articleId, User user) {
        ArticleRowDataDto findArticleDto = articleRepository.findById(articleId).orElseThrow(NoSuchArticleException::new);
        if (!user.getId().equals(findArticleDto.getWriterId())) { throw new NoPermissionException(); }
        return Article.of(findArticleDto);
    }

    // Repository에서 join 한 row 들을 담는 DTO
    public ArticleWithComment getArticleWithComment(Long id) {
        List<ArticleAndCommentRawDataDto> dtos = articleRepository.findWithCommentById(id);
        if (dtos.size() == 0) { throw new NoSuchArticleException(); }
        return ArticleWithComment.from(dtos);
    }

    public boolean updateArticle(Article updateArticle, User user) {
        checkUserPermission(updateArticle.getId(), user);
        ArticleRowDataDto articleRowDataDto = ArticleRowDataDto.from(updateArticle, user.getId());
        if (articleRepository.update(articleRowDataDto) <= 0) { throw new RuntimeException("업데이트에 실패했습니다."); }
        return true;
    }

    public boolean deleteArticle(Long articleId, User user) {
        checkArticleDeletePermission(articleId, user);
        return articleRepository.deleteById(articleId);
    }

    private void checkArticleDeletePermission(Long articleId, User user) {
        if (!articleRepository.checkCanDelete(articleId, user.getId())) { throw new NoPermissionException(); }
    }
}
