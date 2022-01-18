package com.kakao.cafe.articles;

import com.kakao.cafe.exceptions.NotFoundException;
import com.kakao.cafe.users.UserDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ArticleService {
    private final ArticleRepository articleRepository;

    public ArticleService(MemoryArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public ArticleDto createArticle(ArticleRequest articleRequest, UserDto userDto) {
        int articleId = articleRepository.getSize() + 1;

        ArticleContent content = new ArticleContent(articleRequest.getContent());
        Article article = new Article((long) articleId, articleRequest.getTitle(), content, userDto.getName(), userDto.getId());

        Article savedArticle = articleRepository.save(article);

        return ArticleDto.toDto(savedArticle);
    }

    public ArticleDto getArticleById(Long id) {
        Article article = articleRepository.findById(id).orElseThrow(() -> new NotFoundException("게시글이 존재하지 않습니다."));

        return ArticleDto.toDto(article);
    }

    public List<ArticleDto> getArticles() {
        return articleRepository.findAll().stream().map(ArticleDto::toDto).collect(Collectors.toList());
    }
}
