package com.kakao.cafe.infrastructure.article;

import com.kakao.cafe.domain.article.Article;
import com.kakao.cafe.domain.article.ArticleVo;
import com.kakao.cafe.domain.article.WriteArticlePort;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

import static com.kakao.cafe.infrastructure.article.FindArticleInMemoryAdaptor.articles;
import static com.kakao.cafe.infrastructure.article.FindArticleInMemoryAdaptor.index;

@Repository
public class WriteArticleInMemoryAdaptor implements WriteArticlePort {

    @Override
    public void save(ArticleVo article) {
        articles.add(
                new Article(index.incrementAndGet(),
                        article.getWriter(),
                        LocalDateTime.now(),
                        article.getTitle(),
                        article.getContents()));
    }

}
