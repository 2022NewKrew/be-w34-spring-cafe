package com.kakao.cafe.article.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

import com.kakao.cafe.article.domain.Article;
import com.kakao.cafe.article.domain.ArticleRepository;
import com.kakao.cafe.article.service.dto.AllArticlesListServiceResponse;
import com.kakao.cafe.article.service.dto.ArticleReadServiceResponse;
import com.kakao.cafe.user.domain.UserRepository;

@Service
@RequiredArgsConstructor
@Slf4j
public class ArticleService {

    private final ArticleRepository articleRepository;
    private final UserRepository userRepository;

    @PostConstruct
    private void init() {
        createArticle(1L, "게시물 제목입니다.", "이것은 게시물 입니다.");
        createArticle(4L, "새로운 게시물입니다.", "이것도 게시물 입니다.");
        log.info("Add basic article data: 게시물 제목입니다. 새로운 게시물입니다.");
    }

    public void createArticle(Long authorId, String title, String contents) {
        articleRepository.persist(makeArticle(authorId, title, contents));
    }

    // 페이징 구현할 때 리펙토링 필요
    public AllArticlesListServiceResponse getAllArticleViewDTO(Long startIndex) {
        List<Article> allArticles = articleRepository.findAll();
        List<Article> result = allArticles.subList(startIndex.intValue(), allArticles.size());
        Collections.reverse(result);
        List<String> authorList = result.stream()
                                        .map(article -> userRepository.find(article.getAuthorId())
                                                                      .orElseThrow(
                                                                              () -> new IllegalArgumentException(
                                                                                      "존재하지 않는 사용자입니다."))
                                                                      .getStringId())
                                        .collect(Collectors.toList());
        return new AllArticlesListServiceResponse(result, authorList);
    }

    public ArticleReadServiceResponse getArticleReadViewDTO(Long id) {
        articleRepository.increaseHit(id);
        Article article = articleRepository.find(id)
                                           .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시글 입니다."));
        String authorStringId = userRepository.find(article.getAuthorId())
                                              .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자입니다."))
                                              .getStringId();
        return ArticleServiceDTOMapper.convertToArticleReadServiceResponse(article, authorStringId);
    }

    private Article makeArticle(Long authorId, String title, String contents) {
        return Article.builder()
                      .title(title)
                      .authorId(authorId)
                      .contents(contents)
                      .hits(0)
                      .build();
    }

    public void isAuthorOfArticle(Long articleId, Long authorId) {
        Optional<Article> article = articleRepository.find(articleId);
        Long id = article.orElseThrow(() -> new IllegalArgumentException("존재하지 않는 글입니다.")).getAuthorId();
        if (id != authorId) {
            throw new IllegalArgumentException("작성자 본인만 수정 가능합니다.");
        }
    }

    public void updateArticle(long articleId, String title, String contents) {
        Optional<Article> findArticle = articleRepository.find(articleId);
        Article article = findArticle.orElseThrow(() -> new IllegalArgumentException("존재하지 않는 글입니다."));
        articleRepository.updateArticle(makeUpdatingArticle(article, title, contents));
    }

    private Article makeUpdatingArticle(Article article, String title, String contents) {
        return Article.builder()
                      .title(title)
                      .id(article.getId())
                      .authorId(article.getAuthorId())
                      .contents(contents)
                      .hits(article.getHits())
                      .date(article.getDate())
                      .build();
    }

    // 나중에 페이징 구현할 때 활용
//    public AllArticlesListServiceResponse getAllArticleViewDTO(Long startIndex, Long endIndex) {
//        ArrayList<Article> articleList = articleRepository.findAll();
//        if (startIndex < 0) {
//            startIndex = 0L;
//        }
//        if (endIndex > articleList.size()) {
//            endIndex = articleList.size() + 1L;
//        }
//        if (startIndex > articleList.size() || startIndex >= endIndex) {
//            return new AllArticlesListServiceResponse(new ArrayList<Article>(), new ArrayList<String>());
//        }
//        Stream<Article> stream = articleList.stream();
//        if (startIndex > 0) {
//            stream = stream.skip(startIndex);
//        }
//        articleList = stream.limit(endIndex - startIndex).collect(Collectors.toCollection(ArrayList::new));
//        Collections.reverse(articleList);
//        ArrayList<String> authorList = articleList.stream().map(article -> userRepository.find(article.getAuthorId()).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자 입니다.")).getStringId()).collect(Collectors.toCollection(ArrayList::new));
//
//        return new AllArticlesListServiceResponse(articleList, authorList);
//    }
}
