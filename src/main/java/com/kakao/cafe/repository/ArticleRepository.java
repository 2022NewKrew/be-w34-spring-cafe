package com.kakao.cafe.repository;

import com.kakao.cafe.domain.Article;
import com.kakao.cafe.dao.ArticleDao;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ArticleRepository {
    Map<Long, ArticleDao> articleMap;
    Long lastNumber;

    public ArticleRepository() {
        articleMap = new HashMap<>();
        lastNumber = 1L;
        this.insert(new Article("아이고난", "게딱지는 일반쓰레기일까요 음식물쓰레기일까요", "정답은 킹반갓레기.\n게딱지는 딱딱해서 동물들이 먹을스가 읎어요"));
        this.insert(new Article("Mr.Pizza", "도미노 피자 할인 무슨요일이죠?", "도미노 피자 빨리 먹고 싶다"));
    }

    public void insert(Article article) {
        ArticleDao articleDao = toArticleDao(article);
        articleMap.put(articleDao.getNumber(), articleDao);
    }

    public List<ArticleDao> selectAll() {
        return new ArrayList<>(articleMap.values());
    }

    public ArticleDao toArticleDao(Article article) {
        Long datetime = System.currentTimeMillis();
        Timestamp timestamp = new Timestamp(datetime);
        return new ArticleDao(lastNumber++, article.getWriter(), article.getTitle(), article.getContents(), timestamp);
    }

    public ArticleDao select(Long id) {
        return articleMap.get(id);
    }
}
