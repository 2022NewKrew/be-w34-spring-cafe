package com.kakao.cafe.service;

import com.kakao.cafe.domain.Article;
import com.kakao.cafe.dto.ArticleDto;
import com.kakao.cafe.repo.ArticleRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;

@Service
public class ArticleManager implements ArticleService {
    private final ArticleRepository articleRepository;

    ArticleManager(final ArticleRepository articleRepository) {
        this.articleRepository = Objects.requireNonNull(articleRepository);
    }

    @Override
    public void add(@NonNull final ArticleDto articleDto) {
        boolean result = articleRepository.add(new Article(
                articleDto.getUserId(),
                articleDto.getTitle(),
                articleDto.getBody()
        ));

        if (!result) {
            throw new RuntimeException("Failed to insert article!");
        }
    }

    @Override
    public List<ArticleDto> getDtoList() {
        return articleRepository.getDtoList();
    }

    @Override
    public ArticleDto getDto(final long idx) throws NoSuchElementException {
        final Optional<ArticleDto> optional = Optional.ofNullable(articleRepository.getDto(idx));
        if (optional.isEmpty()) {
            throw new NoSuchElementException("Not found article - " + idx);
        }

        return optional.get();
    }

    @Override
    public boolean update(@NonNull final ArticleDto articleDto) {
        final long idx = articleDto.getIdx();

        try {
            getDto(idx);
        } catch (NoSuchElementException e) {
            return false;
        }

        return articleRepository.update(
                idx,
                new Article("dummyid", articleDto.getTitle(), articleDto.getBody())
        );
    }

    @Override
    public boolean delete(@NonNull final long idx) {
        try {
            getDto(idx);
        } catch (NoSuchElementException e) {
            return false;
        }

        return articleRepository.delete(idx);
    }
}
