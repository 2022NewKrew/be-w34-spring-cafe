package com.kakao.cafe.service.article;

import com.kakao.cafe.dao.article.ArticleDao;
import com.kakao.cafe.model.article.Article;
import com.kakao.cafe.model.article.ArticleFactory;
import com.kakao.cafe.service.Constant;
import com.kakao.cafe.service.article.dto.ArticleCreateDto;
import com.kakao.cafe.service.article.dto.ArticleDto;
import com.kakao.cafe.service.article.dto.ArticleUpdateDto;
import com.kakao.cafe.service.reply.dto.ReplyDto;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import javax.naming.NoPermissionException;
import org.springframework.stereotype.Service;

@Service
public class ArticleService {

    private final ArticleDao articleDao;

    public ArticleService(ArticleDao articleDao) {
        this.articleDao = articleDao;
    }

    public List<ArticleDto> getPartOfArticles(int pageNumber, int articlesPerPage) {
        List<Article> articles = articleDao.getArticles(pageNumber, articlesPerPage);
        return articles
                .stream()
                .map(ArticleDto::new)
                .collect(Collectors.toList());
    }

    public List<Integer> getPages(int articleLimit, int pageLimit, int page) {
        int startIndex = calculatePageBaseNumber(page, pageLimit) + 1;
        int finishIndex = calculatePageBaseNumber(page, pageLimit) + pageLimit;
        int lastPageNumber = getLastPageNumber(articleLimit);

        if (lastPageNumber < finishIndex) {
            finishIndex = lastPageNumber;
        }

        return IntStream
                .rangeClosed(startIndex, finishIndex)
                .boxed()
                .collect(Collectors.toList());
    }

    public int getLastPageNumber(int articleLimit) {
        return (articleDao.getSize() - 1) / articleLimit + 1;
    }

    public void createArticle(ArticleCreateDto articleCreateDto) {
        articleDao.addArticle(ArticleFactory.getArticle(articleCreateDto));
    }

    public void deleteArticle(int id, String userId) throws NoPermissionException {
        checkArticleWriter(id, userId);
        if (isArticleHasOnlyUserIdReply(id, userId)) {
            articleDao.deleteArticle(id);
            return;
        }
        throw new NoPermissionException(Constant.OTHER_REPLY_EXIST);
    }

    public void updateArticle(int id, ArticleUpdateDto articleUpdateDto) {
        Article article = ArticleFactory.getArticle(id, articleUpdateDto);

        checkExist(id);

        articleDao.updateArticle(article);
    }

    public ArticleDto findArticleById(int id) {
        return new ArticleDto(findArticle(id));
    }

    public List<ReplyDto> getArticleReplies(int articleId) {
        return articleDao
                .findArticleById(articleId)
                .orElseThrow(() -> new IllegalArgumentException(Constant.ARTICLE_IS_NOT_EXIST))
                .getReplies()
                .stream()
                .map(ReplyDto::new)
                .collect(Collectors.toList());
    }

    private Article findArticle(int id) {
        return articleDao
                .findArticleById(id)
                .orElseThrow(() -> new IllegalArgumentException(Constant.ARTICLE_IS_NOT_EXIST));
    }

    private void checkExist(int id) {
        if (articleDao.findArticleById(id).isEmpty()) {
            throw new IllegalArgumentException(Constant.ARTICLE_IS_NOT_EXIST);
        }
    }

    private void checkArticleWriter(int id, String userId) throws NoPermissionException {
        Article article = articleDao.findArticleById(id)
                .orElseThrow(() -> new IllegalArgumentException(Constant.ARTICLE_IS_NOT_EXIST));

        if (!article.getUserId().getValue().equals(userId)) {
            throw new NoPermissionException(Constant.DELETE_ARTICLE_ONLY_WRITER);
        }
    }

    private boolean isArticleHasOnlyUserIdReply(int articleId, String userId) {
        return articleDao
                .findArticleById(articleId)
                .orElseThrow(() -> new IllegalArgumentException(Constant.ARTICLE_IS_NOT_EXIST))
                .hasOnlyReplyByWriter();
    }

    private int calculatePageBaseNumber(int page, int pageLimit) {
        return Math.floorDiv(page - 1, pageLimit) * pageLimit;
    }
}
