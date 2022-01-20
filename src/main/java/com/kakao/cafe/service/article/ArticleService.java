package com.kakao.cafe.service.article;

import com.kakao.cafe.common.exception.custom.DeleteFailedException;
import com.kakao.cafe.common.exception.custom.UpdateFailedException;
import com.kakao.cafe.common.exception.custom.UserNotFoundException;
import com.kakao.cafe.common.exception.data.ErrorCode;
import com.kakao.cafe.domain.User;
import com.kakao.cafe.repository.user.UserRepository;
import com.kakao.cafe.service.article.mapper.ArticleDtoMapper;
import com.kakao.cafe.domain.Article;
import com.kakao.cafe.repository.article.ArticleRepository;
import com.kakao.cafe.service.article.dto.ArticleInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;
    private final UserRepository userRepository;
    private final ArticleDtoMapper articleDtoMapper;

    public List<ArticleInfo> getArticleAll() {
        List<Article> articles = articleRepository.findAll();
        return articleDtoMapper.toArticleInfoList(articles);
    }

    public ArticleInfo getArticleInfo(Long articleId) {
        Article article = articleRepository.findById(articleId).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다."));
        return articleDtoMapper.toArticleInfo(article);
    }

    public Long write(String writerId, String title, String contents) {
        User findUser = userRepository.findByUserId(writerId).orElseThrow(() -> new UserNotFoundException(ErrorCode.USER_NOT_FOUND));
        Article article = Article.of(findUser, title, contents);
        return articleRepository.insert(article);
    }

    public Long update(String updaterId, Long articleId, String title, String contents) {
        Article updateArticle = articleRepository.findById(articleId).orElseThrow(() -> new UpdateFailedException(ErrorCode.ARTICLE_NOT_FOUND));
        if(!updateArticle.isWriter(updaterId)) {
            throw new UpdateFailedException(ErrorCode.ARTICLE_UPDATER_INCORRECT);
        }
        updateArticle.update(title, contents);
        return articleRepository.update(updateArticle);
    }

    public void delete(Long articleId, String userId) {
        Article deleteArticle = articleRepository.findById(articleId).orElseThrow(() -> new UpdateFailedException(ErrorCode.ARTICLE_NOT_FOUND));
        if(!deleteArticle.isWriter(userId)) {
            throw new DeleteFailedException(ErrorCode.ARTICLE_DELETER_INCORRECT);
        }
        articleRepository.delete(articleId);
    }
}
