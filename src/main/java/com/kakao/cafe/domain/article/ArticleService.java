package com.kakao.cafe.domain.article;

import com.kakao.cafe.core.exception.CannotDeleteArticle;
import com.kakao.cafe.core.exception.IsNotAuthorOfThisArticle;
import com.kakao.cafe.domain.article.dto.ArticleResponse;
import com.kakao.cafe.domain.article.dto.ArticleSaveForm;
import com.kakao.cafe.domain.article.dto.ArticleUpdateForm;
import com.kakao.cafe.domain.article.dto.PageInfo;
import com.kakao.cafe.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;

    public List<ArticleResponse> findAll() {
        return articleRepository.findAll().stream().map(ArticleResponse::from).collect(Collectors.toList());
    }

    public ArticleResponse findById(Long id) {
        Article byId = articleRepository.findById(id);
        return ArticleResponse.from(byId);
    }

    public void save(ArticleSaveForm articleSaveForm, User user) {
        articleSaveForm.setAuthorInfo(user);
        articleRepository.save(articleSaveForm);
    }

    public ArticleUpdateForm getUpdateForm(Long id, Long userId) throws IsNotAuthorOfThisArticle {
        isAuthor(id, userId);
        Article byId = articleRepository.findById(id);

        return ArticleUpdateForm.from(byId);
    }

    public void delete(Long id, Long userId) throws IsNotAuthorOfThisArticle, CannotDeleteArticle {
        canDeleteAndCheckAuthority(id, userId);

        articleRepository.delete(id);
    }

    private void canDeleteAndCheckAuthority(Long id, Long userId) {
        if(!articleRepository.canDeleteAndCheckAuthority(id, userId)) {
            throw new CannotDeleteArticle();
        }
    }

    public void update(Long id, ArticleUpdateForm updateForm, Long userId) throws IsNotAuthorOfThisArticle{
        isAuthor(id, userId);

        articleRepository.update(id, updateForm);
    }

    private void isAuthor(Long id, Long userId) throws IsNotAuthorOfThisArticle{
        if(!articleRepository.checkAuthor(id, userId)) {
            throw new IsNotAuthorOfThisArticle();
        }
    }

    public List<ArticleResponse> getListByPagenum(int pageNum) {
        List<Article> byPage = articleRepository.findByPage(pageNum);
        return byPage.stream().map(ArticleResponse::from).collect(Collectors.toList());
    }

    public PageInfo getPageInfo() {
        articleRepository.getPageCnt();
        return null;
    }
}
