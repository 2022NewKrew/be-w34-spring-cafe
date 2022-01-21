package com.kakao.cafe.domain.article.dto;

import com.kakao.cafe.domain.article.ArticleWithComment;
import com.kakao.cafe.domain.comment.dto.CommentResponseDto;
import com.kakao.cafe.domain.user.User;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

// article 과 각 comment 에 isWriter 값을 가지도록 한 ReponseDto(view에서 수정, 삭제 버튼 노출을 위해)
@Getter
public class ArticleWithCommentResponseDto {

    private Long id;
    private String writerUserId;
    private String title;
    private String contents;
    private LocalDateTime registerDateTime;

    private boolean isWriter = false;

    private int commentSize;
    private List<CommentResponseDto> commentResponseDtoList;

    @Builder
    public ArticleWithCommentResponseDto(Long id, String writerUserId, String title, String contents, LocalDateTime registerDateTime) {
        this.id = id;
        this.writerUserId = writerUserId;
        this.title = title;
        this.contents = contents;
        this.registerDateTime = registerDateTime;
    }

    public void setIsWriter(boolean writer) {
        isWriter = writer;
    }

    public void setCommentResponseDtoList(List<CommentResponseDto> commentResponseDtoList) {
        this.commentResponseDtoList = commentResponseDtoList;
    }

    public int getCommentSize() {
        return commentSize;
    }

    public void setCommentSize(int commentSize) {
        this.commentSize = commentSize;
    }

    public List<CommentResponseDto> getCommentResponseDtoList() {
        return commentResponseDtoList;
    }

    public static ArticleWithCommentResponseDto from(ArticleWithComment articleWithComment, User user) {
        ArticleWithCommentResponseDto dto = builder()
                .id(articleWithComment.getId())
                .writerUserId(articleWithComment.getWriterUserId())
                .title(articleWithComment.getTitle())
                .contents(articleWithComment.getContents())
                .registerDateTime(articleWithComment.getRegisterDateTime())
                .build();

        List<CommentResponseDto> commentResponseDtoList = articleWithComment.getComments().stream()
                .map(CommentResponseDto::from)
                .collect(Collectors.toList());

        dto.setCommentResponseDtoList(commentResponseDtoList);
        dto.setCommentSize(commentResponseDtoList.size());

        // isWriter 필드 업데이트
        if (user.getUserId().equals(dto.getWriterUserId())) { dto.setIsWriter(true); }
        dto.getCommentResponseDtoList().stream()
                .forEach(comment -> comment.setIsWriter(user.getUserId()));

        return dto;
    }

}
