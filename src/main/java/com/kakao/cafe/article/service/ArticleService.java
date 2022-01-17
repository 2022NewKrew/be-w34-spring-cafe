package com.kakao.cafe.article.service;

import com.kakao.cafe.article.domain.Article;
import com.kakao.cafe.article.repository.ArticleCreateRequestDTO;
import com.kakao.cafe.article.domain.ArticleRepository;
import com.kakao.cafe.user.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;
    private final UserRepository userRepository;

    public void createArticle(Long authorId, String title, String contents ){
        articleRepository.persist(new ArticleCreateRequestDTO(title, authorId, contents));
    }

    public AllArticlesResponseDTO getAllArticleViewDTO(Long startIndex, Long endIndex ){
        ArrayList<Article> articleList = articleRepository.findAll();
        if (startIndex < 0) {
            startIndex = 0L;
        }
        if (endIndex > articleList.size()) {
            endIndex = articleList.size() + 1L;
        }
        if (startIndex > articleList.size() || startIndex >= endIndex) {
            return new AllArticlesResponseDTO(new ArrayList<Article>(), new ArrayList<String>());
        }
        Stream<Article> stream = articleList.stream();
        if (startIndex > 0) {
            stream = stream.skip(startIndex);
        }
        articleList = stream.limit(endIndex - startIndex).collect(Collectors.toCollection(ArrayList::new));
        Collections.reverse(articleList);
        ArrayList<String> authorList = articleList.stream().map(article->userRepository.find(article.getAuthorId()).orElseThrow(()->new IllegalArgumentException("존재하지 않는 사용자 입니다.")).getStringId()).collect(Collectors.toCollection(ArrayList::new));

        return new AllArticlesResponseDTO(articleList, authorList);
    }


    public ArticleReadResponseDTO getArticleReadViewDTO(Long id) {
        articleRepository.increaseHit(id);
        Article article = articleRepository.find(id).orElseThrow(()-> new IllegalArgumentException("존재하지 않는 게시글 입니다."));
        String authorStringId = userRepository.find(id).orElseThrow(()->new IllegalArgumentException("존재하지 않는 사용자입니다.")).getStringId();
        return new ArticleReadResponseDTO(article.getId(), article.getAuthorId(), article.getDate(), article.getHits(), article.getContents(), article.getTitle(), authorStringId);
    }

    public AllArticlesResponseDTO getAllArticleViewDTO(Long startIndex){
        ArrayList<Article> articleList = articleRepository.findAll().stream().skip(startIndex).collect(Collectors.toCollection(ArrayList::new));
        Collections.reverse(articleList);
        ArrayList<String> authorList = articleList.stream().map(article->userRepository.find(article.getAuthorId()).orElseThrow(()->new IllegalArgumentException("존재하지 않는 사용자입니다.")).getStringId()).collect(Collectors.toCollection(ArrayList::new));
        return new AllArticlesResponseDTO(articleList, authorList);
    }
}
