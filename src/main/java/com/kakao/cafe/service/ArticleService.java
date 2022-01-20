package com.kakao.cafe.service;

import com.kakao.cafe.domain.entity.Article;
import com.kakao.cafe.domain.repository.article.ArticleRepository;
import com.kakao.cafe.dto.RequestArticleDto;
import com.kakao.cafe.dto.ResponseArticleDto;
import com.kakao.cafe.exception.ArticleNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ArticleService {

    private final ArticleRepository articleRepository;
    private final ModelMapper modelMapper;

    public ArticleService(@Qualifier("h2ArticleRepository") ArticleRepository articleRepository, ModelMapper modelMapper) {
        this.articleRepository = articleRepository;
        this.modelMapper = modelMapper;
    }

    /*
     * 새 게시글 작성
     */
    public void createArticle(long authorId, RequestArticleDto articleDto) {
        Article article = modelMapper.map(articleDto, Article.class);
        article.setAuthorId(authorId);
        article.setCreatedAt(new Date());
        article.setViews(0L);
        articleRepository.save(article);
    }

    /*
     * 전체 게시글 조회
     */
    public List<ResponseArticleDto> getAllArticles() {
        return articleRepository.findAll().stream()
                .map(article -> modelMapper.map(article, ResponseArticleDto.class))
                .collect(Collectors.toList());
    }

    /*
     * id로 게시글 조회
     */
    public ResponseArticleDto getArticleById(long id) {
        Article article = articleRepository.findById(id).orElseThrow(() -> new ArticleNotFoundException());
        return modelMapper.map(article, ResponseArticleDto.class);
    }

    /*
     * id로 게시글 작성자 id 조회
     */
    public long getAuthorIdOfArticle(long articleId) {
        Article article = articleRepository.findById(articleId).orElseThrow(() -> new ArticleNotFoundException());
        return article.getAuthorId();
    }

    /*
     * id로 게시글 수정
     */
    public void updateArticle(long id, RequestArticleDto articleDto) {
        Article article = articleRepository.findById(id).orElseThrow(() -> new ArticleNotFoundException());
        article.setTitle(articleDto.getTitle());
        article.setContent(articleDto.getContent());
        articleRepository.save(article);
    }

    /*
     * id로 게시글 삭제
     */
    public void deleteArticle(long id) {
        articleRepository.delete(id);
    }

    /*
     * id로 조회수 1 증가
     */
    public void increaseView(long id) {
        Article article = articleRepository.findById(id).orElseThrow(() -> new ArticleNotFoundException());
        article.setViews(article.getViews() + 1);
        articleRepository.save(article);
    }

}
