package com.kakao.cafe.adapter.out.infra.persistence.article;

import com.kakao.cafe.application.article.dto.ArticleList;
import com.kakao.cafe.application.article.port.out.GetArticleInfoPort;
import com.kakao.cafe.application.article.port.out.RegisterArticlePort;
import com.kakao.cafe.domain.article.Article;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Repository;

@Repository
public class StoreArticleInfoAdapter implements RegisterArticlePort, GetArticleInfoPort {

    private final ArticleInfoRepository inMemoryArticleInfoRepository;

    public StoreArticleInfoAdapter(ArticleInfoRepository inMemoryArticleInfoRepository) {
        this.inMemoryArticleInfoRepository = inMemoryArticleInfoRepository;
    }

    @Override
    public void registerArticle(Article article) {
        inMemoryArticleInfoRepository.save(ArticleInfoEntity.from(article));
    }

    @Override
    public ArticleList getListOfAllArticles() {
        List<Article> articleList = inMemoryArticleInfoRepository.getAllArticleList()
                                                                 .stream()
                                                                 .map(a -> new Article(
                                                                     a.getId(),
                                                                     a.getWriter(),
                                                                     a.getTitle(),
                                                                     a.getContents(),
                                                                     a.getCreatedAt()
                                                                 ))
                                                                 .collect(Collectors.toList());

        return ArticleList.from(articleList);
    }

    public Article findArticleByIndex(int index) {
        // TODO : 새로운 Exception 정의 필요
        ArticleInfoEntity articleInfoEntity = inMemoryArticleInfoRepository.findByIndex(index)
                                                                           .orElseThrow(RuntimeException::new);

        return new Article(
            articleInfoEntity.getId(),
            articleInfoEntity.getWriter(),
            articleInfoEntity.getTitle(),
            articleInfoEntity.getContents(),
            articleInfoEntity.getCreatedAt()
        );
    }
}
