package com.kakao.cafe.domain.article;

import com.kakao.cafe.domain.article.dao.ArticleDao;
import com.kakao.cafe.domain.article.dto.ArticleTableRowDto;
import com.kakao.cafe.domain.article.dto.ArticleCreateDto;
import com.kakao.cafe.domain.article.dto.ArticleUpdateDto;
import com.kakao.cafe.domain.article.exception.ArticleNotDeletableException;
import com.kakao.cafe.domain.article.exception.ArticleNotFoundException;
import com.kakao.cafe.domain.comment.dao.CommentDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleDao articleDao;

    private final CommentDao commentDao;

    public Long create(ArticleCreateDto dto) {
        return articleDao.save(dto.toEntity());
    }

    public void update(ArticleUpdateDto dto) {
        Article article = articleDao.findById(dto.getId())
                .orElseThrow(() -> new ArticleNotFoundException(dto.getId()));
        article.updateInfo(dto.toEntity());
        articleDao.update(article);
    }

    public void delete(Long id, Long userId) {
        Article article = articleDao.findById(id)
                .orElseThrow(() -> new ArticleNotFoundException(id));
        article.validateAuthor(userId);

        if(articleDao.checkIfCanNotDelete(article)) { throw new ArticleNotDeletableException(); }

        articleDao.delete(article);
        commentDao.deletebyArticleId(id);
    }

    public ArticleTableRowDto retrieveTableRowById(Long id) {
        return articleDao.retrieveTableRowById(id)
                .orElseThrow(() -> new ArticleNotFoundException(id));
    }

    public ArticleTableRowDto increaseViewCountAndRetrieveTableRowById(Long id) {
        articleDao.increaseViewCount(id);
        return articleDao.retrieveTableRowById(id)
                .orElseThrow(() -> new ArticleNotFoundException(id));
    }

    public List<ArticleTableRowDto> retrieveTableRows() {
        return articleDao.retrieveTableRows();
    }
}
