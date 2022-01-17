package com.kakao.cafe.module.service;

import com.kakao.cafe.infra.exception.NoSuchDataException;
import com.kakao.cafe.module.model.domain.Article;
import com.kakao.cafe.module.model.domain.User;
import com.kakao.cafe.module.repository.ArticleRepository;
import com.kakao.cafe.module.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import static com.kakao.cafe.module.model.dto.ArticleDtos.*;

@Service
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public void postArticle(ArticlePostDto articlePostDto) {
        articlePostDto.setAuthorId(findAuthor(articlePostDto.getAuthor()).getId());
        articleRepository.addArticle(modelMapper.map(articlePostDto, Article.class));
    }

    public ArticleReadDto showArticle(Long id) {
        return articleRepository.findArticleById(id);
    }

    private User findAuthor(String name) {
        return userRepository.findUserByName(name)
                .orElseThrow(() -> new NoSuchDataException("존재하지 않는 작성자입니다."));
    }
}
