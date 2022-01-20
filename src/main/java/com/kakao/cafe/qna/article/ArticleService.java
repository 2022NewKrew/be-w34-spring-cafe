package com.kakao.cafe.qna.article;

import com.kakao.cafe.qna.comment.Comment;
import com.kakao.cafe.qna.comment.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by melodist
 * Date: 2022-01-11 011
 * Time: 오후 1:48
 */
@Service
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;
    private final CommentRepository commentRepository;

    public Article saveArticle(Article article) {
        return articleRepository.save(article);
    }

    public Article findArticleById(Integer id) {
        Article article = articleRepository.findArticleById(id);
        List<Comment> comments = commentRepository.findCommentsByArticleId(id);
        for (Comment comment: comments) {
            article.addComments(comment);
        }

        // 글 ID가 존재하지 않을 경우 예외 발생
        if (article == null) {
            throw new IllegalArgumentException();
        }

        return article;
    }

    public List<Article> findAll() {
        return articleRepository.findAll();
    }

    public Article updateArticle(Integer id, String title, String contents, String userId) {
        Article article = findArticleById(id);

        // 글 작성자 ID와 수정 요청자 ID가 일치해야 함
        if (!userId.equals(article.getWriter())) {
            throw new IllegalArgumentException();
        }

        article.updateContents(title, contents);

        return articleRepository.updateContents(article);
    }

    public Article deleteArticle(Integer id, String userId) {
        Article article = findArticleById(id);

        // 글 작성자 ID와 삭제 요청자 ID가 일치해야 함
        if (!userId.equals(article.getWriter())) {
            throw new IllegalArgumentException();
        }

        article.deleteArticle();

        return articleRepository.updateContents(article);
    }

    public int updateCommentsCount(Integer id, Integer commentsCount) {
        return articleRepository.updateCommentsCount(id, commentsCount);
    }
}
