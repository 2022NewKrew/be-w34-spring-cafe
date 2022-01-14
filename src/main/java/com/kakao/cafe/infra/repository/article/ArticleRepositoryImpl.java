package com.kakao.cafe.infra.repository.article;

import com.kakao.cafe.infra.dao.ArticleDAO;
import com.kakao.cafe.infra.dao.ArticleCreateCommand;
import com.kakao.cafe.web.article.form.ArticleInventoryInfo;
import com.kakao.cafe.web.article.form.ArticlePostInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ArticleRepositoryImpl implements ArticleRepository {
    private final ArticleDAO articleDAO;

    @Autowired
    public ArticleRepositoryImpl(ArticleDAO articleDAO) {
        this.articleDAO = articleDAO;
    }

    @Override
    public void saveArticle(ArticleCreateCommand articleCreateCommand) {
        articleDAO.saveArticle(articleCreateCommand);
    }

    @Override
    public List<ArticleInventoryInfo> getArticleInventoryInfoList() {
        return articleDAO.findArticlesWithoutContents();
    }

    @Override
    public ArticlePostInfo findArticlePostInfo(Long articleId) {
        Optional<ArticlePostInfo> optionalArticlePostInfo = articleDAO.findArticlePostInfo(articleId);
        optionalArticlePostInfo.orElseThrow(() -> new RuntimeException("해당 글을 찾을 수 없습니다."));
        return optionalArticlePostInfo.get();
    }
}
