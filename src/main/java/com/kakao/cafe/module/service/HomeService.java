package com.kakao.cafe.module.service;

import com.kakao.cafe.module.model.domain.User;
import com.kakao.cafe.module.model.mapper.ArticleMapper;
import com.kakao.cafe.module.repository.ArticleRepository;
import com.kakao.cafe.module.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.kakao.cafe.module.model.dto.ArticleDtos.*;

@Service
@RequiredArgsConstructor
public class HomeService {

    private final ArticleRepository articleRepository;
    private final UserRepository userRepository;

    public List<ArticleListDto> articleList() {
        List<ArticleListDto> articleListResult = new ArrayList<>();
        articleRepository.findAllArticles().forEach(article -> {
            User user = userRepository.findUserById(article.getAuthorId());
            articleListResult.add(ArticleMapper.toArticleListDto(article, user));
        });
        return articleListResult;
    }
}
