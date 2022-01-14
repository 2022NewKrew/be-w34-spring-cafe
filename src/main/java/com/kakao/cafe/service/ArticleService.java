package com.kakao.cafe.service;

import com.kakao.cafe.dto.article.ArticleCreateDTO;
import com.kakao.cafe.dto.article.ArticleDTO;
import com.kakao.cafe.dto.user.UserDTO;
import com.kakao.cafe.repository.ArticleMemoryRepository;
import com.kakao.cafe.repository.UserRepository;
import java.util.*;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ArticleService {

    private static final Logger logger = LoggerFactory.getLogger(ArticleService.class);

    private final ArticleMemoryRepository articleRepository;
    private final UserRepository userRepository;

    public Long create(ArticleCreateDTO articleCreateDTO) {
        if(validateUserID(articleCreateDTO.getAuthorId())){
            return Long.MIN_VALUE;
        }

        return articleRepository.save(articleCreateDTO).getId();
    }

    private boolean validateUserID(String userId){
        Optional<UserDTO> user = userRepository.findByUserId(userId);

        return user.isEmpty();
    }

    public List<ArticleDTO> getAllArticles() {
        return articleRepository.findAll();
    }

    public ArticleDTO getArticle(Long id) {
        return articleRepository.findById(id).get();
    }
}