package com.kakao.cafe.service;

import com.kakao.cafe.dao.ArticleDao;
import com.kakao.cafe.dto.PageNumberDto;
import com.kakao.cafe.exception.IncorrectUserException;
import com.kakao.cafe.exception.OtherWriterReplyExistException;
import com.kakao.cafe.util.ErrorUtil;
import com.kakao.cafe.vo.Article;
import com.kakao.cafe.vo.Reply;
import com.kakao.cafe.vo.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ArticleService {

    private final ArticleDao articleDao;
    private final ReplyService replyService;

    public ArticleService(ArticleDao articleDao, ReplyService replyService) {
        this.articleDao = articleDao;
        this.replyService = replyService;
    }

    public void addArticle(Article article, User loginUser) throws IncorrectUserException {
        if(!ErrorUtil.checkSameString(loginUser.getUserId(), article.getWriter()))
            throw new IncorrectUserException();
        articleDao.addArticle(article);
    }

    public List<Article> getArticles() {
        return articleDao.getArticles();
    }

    public Article getArticle(int index) {
        return articleDao.getArticle(index);
    }

    public void updateArticle(int index, Article article, User loginUser) throws IncorrectUserException {
        if(!ErrorUtil.checkSameString(loginUser.getUserId(), article.getWriter()))
            throw new IncorrectUserException();
        articleDao.updateArticle(index, article);
    }

    public void deleteArticle(int index, User loginUser) throws Exception {
        Article article = getArticle(index);
        if(!ErrorUtil.checkSameString(loginUser.getUserId(), article.getWriter()))
            throw new IncorrectUserException();
        List<Reply> replies = replyService.getReplies(index);
        if(!ErrorUtil.checkAllSameWriters(replies, loginUser.getUserId()))
            throw new OtherWriterReplyExistException();
        for(Reply reply : replies)
            replyService.deleteReply(reply.getId(), loginUser);
        articleDao.deleteArticle(index);
    }

    public List<Article> getSubArticles(List<Article> articles, int pageNumber) {
        int startIndex = (pageNumber - 1) * 15;
        int endIndex = Math.min(articles.size(), pageNumber * 15);
        return new ArrayList<>(articles.subList(startIndex, endIndex));
    }

    public List<PageNumberDto> getPageNumbers(List<Article> articles, int pageIndex) {
        List<PageNumberDto> pageNumbers = new ArrayList<>();
        int startPageNumber = pageIndex * 5 + 1;
        int endPageNumber = Math.min((pageIndex + 1) * 5, (articles.size() - 1) / 15 + 1);
        for(int i = startPageNumber; i <= endPageNumber; i++)
            pageNumbers.add(new PageNumberDto(i));
        return pageNumbers;
    }

}
