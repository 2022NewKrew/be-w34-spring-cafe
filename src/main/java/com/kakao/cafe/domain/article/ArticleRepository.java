package com.kakao.cafe.domain.article;

import com.kakao.cafe.domain.article.dto.ArticleSaveForm;
import com.kakao.cafe.domain.article.dto.ArticleUpdateForm;

import java.util.List;

public interface ArticleRepository {
    public void save(ArticleSaveForm article);
    public List<Article> findAll();
    public Article findById(Long postId);
    public void delete(Long id);
    public boolean checkAuthor(Long id, Long userId);
    public void update(Long id, ArticleUpdateForm updateForm);
    public void incrementNumOfComment(Long articleId);
    public void decrementNumOfComment(Long articleId);
    public boolean canDeleteAndCheckAuthority(Long id, Long userId);

    void updateAuthorName(Long id, String name);

    List<Article> findByPage(int pageNum);

    int getPageCnt();
}
