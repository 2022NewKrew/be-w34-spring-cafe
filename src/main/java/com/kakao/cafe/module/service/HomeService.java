package com.kakao.cafe.module.service;

import com.kakao.cafe.module.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.kakao.cafe.module.model.dto.ArticleDtos.*;

@Service
@RequiredArgsConstructor
public class HomeService {

    private final ArticleRepository articleRepository;

    public List<ArticleListDto> articleList() {
        return articleRepository.findAllArticlesWithAuthor();
    }
}
