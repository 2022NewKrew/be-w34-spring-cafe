package com.kakao.cafe.service;

import com.kakao.cafe.entity.Article;
import com.kakao.cafe.dto.ArticleDto;
import com.kakao.cafe.repo.ArticleRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;

@Transactional
@Service
public class ArticleManager implements ArticleService {
    private final ArticleRepository articleRepository;

    ArticleManager(final ArticleRepository articleRepository) {
        this.articleRepository = Objects.requireNonNull(articleRepository);
    }

    @Transactional
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

    @Transactional(readOnly = true)
    @Override
    public List<ArticleDto> getDtoList() {
        return articleRepository.getDtoList();
    }

    @Transactional(readOnly = true)
    @Override
    public ArticleDto getDto(final long idx) throws NoSuchElementException {
        final Optional<ArticleDto> optional = Optional.ofNullable(articleRepository.getDto(idx));
        if (optional.isEmpty()) {
            throw new NoSuchElementException("Not found article - " + idx);
        }

        return optional.get();
    }

    @Transactional(readOnly = true)
    @Override
    public ArticleDto getDto(final long idx, final String currentUserId) throws NoSuchElementException {
        ArticleDto articleDto = getDto(idx);
        articleDto.setOwner(articleDto.getUserId().equals(currentUserId));
        return articleDto;
    }

    @Transactional
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

    @Transactional
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
