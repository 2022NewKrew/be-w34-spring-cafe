package com.kakao.cafe.service;

import com.kakao.cafe.domain.entity.Article;
import com.kakao.cafe.dto.RequestArticleDto;
import com.kakao.cafe.dto.ResponseArticleDto;
import com.kakao.cafe.domain.repository.article.ArticleRepository;
import com.kakao.cafe.domain.repository.article.H2ArticleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ArticleService {
    @Autowired
    private ModelMapper modelMapper;
    private final ArticleRepository articleRepository;

    public ArticleService(@Qualifier("h2ArticleRepository") H2ArticleRepository articleRepository){
        this.articleRepository = articleRepository;
    }

    /*
    * 새 게시글 작성
    */
    public void addPost(RequestArticleDto articleDto) {
        Article article = modelMapper.map(articleDto, Article.class);
        article.setCreatedAt(new Date());
        article.setViews(0);
        articleRepository.save(article);
    }

    /*
    * 전체 게시글 조회
    */
    public List<ResponseArticleDto> findArticles() {

        return articleRepository.findAll().stream()
                .map(article -> modelMapper.map(article, ResponseArticleDto.class))
                .collect(Collectors.toList());
    }

    /*
    * id로 게시글 조회
    */
    public ResponseArticleDto findOne(long id) {
        Article article = articleRepository.findById(id).orElseThrow(() -> new IllegalStateException("해당 글이 존재하지 않습니다."));
        return modelMapper.map(article, ResponseArticleDto.class);
    }

}
