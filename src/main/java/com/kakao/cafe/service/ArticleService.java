package com.kakao.cafe.service;

import com.kakao.cafe.domain.Article;
import com.kakao.cafe.dto.article.ArticleCreationDto;
import com.kakao.cafe.dto.article.ArticleDto;
import com.kakao.cafe.repository.article.ArticleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ArticleService {

    private final ArticleRepository articleRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public ArticleService(ArticleRepository articleRepository, ModelMapper modelMapper) {
        this.articleRepository = articleRepository;
        this.modelMapper = modelMapper;
    }

    public long post(ArticleCreationDto dto) {
        Article newArticle = new Article(dto);
        newArticle = articleRepository.save(newArticle);

        return newArticle.getId();
    }

    public long update(long id, long sessionUid, ArticleCreationDto dto) throws Exception {
        var article = articleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시글입니다."));
        validateAuth(sessionUid, article.getUserId());
        article.setTitle(dto.getTitle());
        article.setBody(dto.getBody());
        articleRepository.save(article);

        return article.getId();
    }

    public void delete(long id, long sessionUid) throws Exception {
        long uid = articleRepository.findUidById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시글입니다."));
        validateAuth(sessionUid, uid);
        articleRepository.delete(id);
    }

    public List<ArticleDto> getAllArticles() {
        return articleRepository.findAll().stream()
                .map(m -> modelMapper.map(m, ArticleDto.class))
                .collect(Collectors.toList());
    }

    public ArticleDto getById(long id) {
        return articleRepository.findById(id)
                .map(m -> modelMapper.map(m, ArticleDto.class))
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시물입니다."));
    }

    public String findNicknameById(long id) {
        return articleRepository.findUserNicknameById(id)
                .orElse("탈퇴한 사용자");
    }

    private void validateAuth(long articleUid, long sessionUid) throws IllegalAccessException {
        if (sessionUid != articleUid) {
            throw new IllegalAccessException("해당 게시글에 대한 쓰기 권한이 없습니다.");
        }
    }
}
