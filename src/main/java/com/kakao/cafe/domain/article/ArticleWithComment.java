package com.kakao.cafe.domain.article;

import com.kakao.cafe.domain.article.dto.ArticleAndCommentRawDataDto;
import com.kakao.cafe.domain.comment.Comment;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

// article 과 List<Comment> 를 모두 가지고 있는 ArticleWithComment 객체
public class ArticleWithComment extends Article{

    public ArticleWithComment() {
    }

    private Long commentId;

    private List<Comment> comments = new ArrayList<>();

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public Long getCommentId() {
        return commentId;
    }

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }

    public static ArticleWithComment from(List<ArticleAndCommentRawDataDto> dtos) {
        ArticleAndCommentRawDataDto dto = dtos.get(0);
        ArticleWithComment articleWithComment = new ArticleWithComment();
        articleWithComment.setId(dto.getId());
        articleWithComment.setWriterUserId(dto.getAuthorUserId());
        articleWithComment.setTitle(dto.getTitle());
        articleWithComment.setContents(dto.getContents());
        articleWithComment.setRegisterDateTime(dto.getArticleRegisterDateTime());

        List<Comment> comments = dtos.stream()
                .filter(Comment::checkNull)
                .map(Comment::from)
                .collect(Collectors.toList());

        articleWithComment.setComments(comments);

        return articleWithComment;
    }
}
