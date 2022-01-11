package com.kakao.cafe.service;

import com.kakao.cafe.domain.Article;
import com.kakao.cafe.dto.ArticleFormDTO;
import com.kakao.cafe.dto.ArticleShowDTO;
import com.kakao.cafe.dto.ArticleShowListDTO;
import com.kakao.cafe.mapper.ArticleMapper;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ArticleService {
    private final ModelMapper modelMapper;
    private final ArticleMapper articleMapper;

    public List<Article> findAll() {
        return articleMapper.selectAll();
    }

    public List<ArticleShowListDTO> findAllShowListDTO() {
        return articleMapper.selectAllForList();
    }

    public ArticleShowDTO findByKeyForShow(long key) {
        return articleMapper.selectByKeyForShow(key);
    }

    public long join(ArticleFormDTO articleFormDTO) {
        Article article = modelMapper.map(articleFormDTO, Article.class);
        article.setPostTime(LocalDateTime.now());
        return articleMapper.insert(article);
    }
}
