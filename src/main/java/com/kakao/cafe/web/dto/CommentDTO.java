package com.kakao.cafe.web.dto;

import com.kakao.cafe.domain.Comment;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentDTO {

  private String comments;

  public CommentDTO(Comment comment) {
    this.comments = comment.getContents();
  }

  @Override
  public String toString() {
    return "CommentDTO{" +
        "comments='" + comments + '\'' +
        '}';
  }
}
