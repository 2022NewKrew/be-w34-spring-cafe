package com.kakao.cafe.service.article;

import com.kakao.cafe.domain.Entity.Article;
import com.kakao.cafe.domain.Repository.article.ArticleRepository;
import com.kakao.cafe.dto.article.ReferArticleDto;
import com.kakao.cafe.dto.article.PostArticleDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ArticleService {

    private ArticleRepository articleRepository;

    // 게시물 저장
    public void save(PostArticleDto postArticleDto) {
        this.articleRepository.save(postArticleDto);
    }

    // 전체 게시물 찾기
    public List<ReferArticleDto> findAll() {
        List<ReferArticleDto> articles = this.articleRepository.findAll().stream()
                .map(ReferArticleDto::new).collect(Collectors.toList());
        return articles;
    }

    // id로 해당 게시물 찾기
    public ReferArticleDto findById(int id) {
        Article article = this.articleRepository.findById(id);
        return new ReferArticleDto(article);
    }

    public String getPostedUserById(int id) {
        Article article = this.articleRepository.findById(id);
        return article.getUserId();
    }

    // 게시물 수정
    public void update(PostArticleDto postArticleDto, int id) {
        this.articleRepository.update(id, postArticleDto.getTitle(), postArticleDto.getContents());
    }

    // 게시물 삭제
    public void delete(int id) {
        this.articleRepository.delete(id);
    }
}
