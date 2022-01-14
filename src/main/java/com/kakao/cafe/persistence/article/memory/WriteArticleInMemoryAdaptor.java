package com.kakao.cafe.persistence.article.memory;

import com.kakao.cafe.domain.article.Article;
import com.kakao.cafe.domain.article.ArticleVo;
import com.kakao.cafe.domain.article.WriteArticlePort;

import java.time.LocalDateTime;

import static com.kakao.cafe.persistence.article.memory.FindArticleInMemoryAdaptor.articles;
import static com.kakao.cafe.persistence.article.memory.FindArticleInMemoryAdaptor.index;

//@Repository
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
