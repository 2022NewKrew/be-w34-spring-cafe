package com.kakao.cafe.service.validation;

import com.kakao.cafe.exception.article.ArticleNotFoundException;
import com.kakao.cafe.exception.user.NotAllowedUserException;
import com.kakao.cafe.model.dto.UserDto;
import com.kakao.cafe.model.vo.ArticleVo;
import com.kakao.cafe.model.vo.CommentVo;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ArticleValidation {

    public void validateArticle(ArticleVo article) {
        if (article == null) {
            throw new ArticleNotFoundException();
        }
    }

    public void validateDeleteArticle(List<CommentVo> comments, UserDto user) {
        List<CommentVo> otherUserComments = comments.stream()
                .filter(comment -> !user.getUserId().equals(comment.getWriter().getUserId()))
                .collect(Collectors.toList());
        if (otherUserComments.size() != 0) {
            throw new NotAllowedUserException();
        }
    }
}
