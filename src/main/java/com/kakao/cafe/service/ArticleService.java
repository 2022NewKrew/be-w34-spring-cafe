package com.kakao.cafe.service;

import com.kakao.cafe.dto.ArticleDto;
import com.kakao.cafe.entity.Article;
import com.kakao.cafe.entity.User;
import com.kakao.cafe.repository.ArticleRepository;
import com.kakao.cafe.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ArticleService {

    private final ArticleRepository repository;
    private final UserRepository userRepository;

    @Autowired
    public ArticleService(ArticleRepository repository, UserRepository userRepository) {
        this.repository = repository;
        this.userRepository = userRepository;
    }

    public ArticleDto create(long ownerId, String author, String title, String content) {
        User owner = userRepository.getById(ownerId);
        Article entity = new Article.Builder()
                .owner(owner)
                .author(author)
                .title(title)
                .content(content)
                .build();
        return repository.create(entity).toDto();
    }

    public List<ArticleDto> list() {
        return repository.list()
                .stream()
                .map(Article::toDto)
                .collect(Collectors.toList());
    }

    @Nullable
    public ArticleDto getById(long id) {
        Article found = repository.getById(id);
        if (found == null) {
            return null;
        }
        return found.toDto();
    }
}
