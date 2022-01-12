package com.kakao.cafe.adapter.out.infra.persistence.article;

import com.kakao.cafe.application.article.dto.ArticleDetail;
import com.kakao.cafe.application.article.dto.ArticleList;
import com.kakao.cafe.application.article.dto.ArticleListEntry;
import com.kakao.cafe.application.article.port.out.GetArticleInfoPort;
import com.kakao.cafe.application.article.port.out.RegisterArticlePort;
import com.kakao.cafe.domain.article.Article;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Repository;

@Repository
public class StoreArticleInfoAdapter implements RegisterArticlePort, GetArticleInfoPort {

    private final ArticleInfoRepository articleInfoRepository;

    public StoreArticleInfoAdapter(ArticleInfoRepository articleInfoRepository) {
        this.articleInfoRepository = articleInfoRepository;
    }

    @Override
    public void registerArticle(Article article) {
        articleInfoRepository.save(ArticleInfoEntity.from(article));
    }

    @Override
    public ArticleList getListOfAllArticles() {
        List<ArticleListEntry> articleInfoList = articleInfoRepository.getAllArticleList()
                                                                      .stream()
                                                                      .map(a -> new ArticleListEntry(
                                                                          a.getIndex(),
                                                                          a.getWriter(),
                                                                          a.getTitle(),
                                                                          a.getPostedDate()
                                                                           .format(DateTimeFormatter.ofPattern(
                                                                               "yyyy-MM-dd HH:mm"))
                                                                      ))
                                                                      .collect(Collectors.toList());

        return ArticleList.from(articleInfoList);
    }

    public ArticleDetail findArticleByIndex(int index) {
        ArticleInfoEntity articleInfoEntity = articleInfoRepository.findByIndex(index)
                                                                   .orElseThrow(RuntimeException::new);        // TODO : 새로운 Exception 정의 필요

        return new ArticleDetail(
            articleInfoEntity.getIndex(),
            articleInfoEntity.getWriter(),
            articleInfoEntity.getTitle(),
            articleInfoEntity.getContents(),
            articleInfoEntity.getPostedDate()
                             .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))
        );
    }
}
