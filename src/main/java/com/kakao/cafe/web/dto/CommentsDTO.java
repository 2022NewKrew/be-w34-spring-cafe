package com.kakao.cafe.web.dto;

import com.kakao.cafe.domain.Comments;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentsDTO implements Iterable<CommentDTO> {

  private List<CommentDTO> comments;


  public CommentsDTO() {
    this.comments = new ArrayList<>();
  }

  public CommentsDTO(Comments comments) {
    this.comments = comments.stream()
        .map(CommentDTO::new)
        .collect(Collectors.toList());
  }


  @Override
  public Iterator<CommentDTO> iterator() {
    return comments.iterator();
  }


  @Override
  public void forEach(Consumer<? super CommentDTO> action) {
    comments.forEach(action);
  }


  public Stream<CommentDTO> stream() {
    return comments.stream();
  }


  @Override
  public String toString() {
    return "CommentsDTO{" +
        "comments=" + comments +
        '}';
  }

}
