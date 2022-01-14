package com.kakao.cafe.domain;

import com.kakao.cafe.web.dto.CommentDTO;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Comment {

  //TODO: 여유로울 때 구체화 할 것.
  private String contents;

  public static Comment of(CommentDTO commentDTO) {
    return new Comment(commentDTO.getComments());
  }

}
