package com.kakao.cafe.service;

import com.kakao.cafe.dto.ArticleDto;
import com.kakao.cafe.entity.Article;
import com.kakao.cafe.entity.User;
import com.kakao.cafe.repository.ArticleRepository;
import com.kakao.cafe.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArticleService {

    private final ArticleRepository repository;
    private final UserRepository userRepository;

    @Autowired
    public ArticleService(ArticleRepository repository, UserRepository userRepository) {
        this.repository = repository;
        this.userRepository = userRepository;
    }

    public ArticleDto create(String ownerId, String author, String title, String content) {
        User owner = userRepository.getById(ownerId);
        Article entity = new Article.Builder()
                .owner(owner)
                .author(author)
                .title(title)
                .content(content)
                .build();
        return repository.create(entity).toDto();
    }
}
