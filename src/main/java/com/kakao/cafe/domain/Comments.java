package com.kakao.cafe.domain;

import com.kakao.cafe.web.dto.CommentsDTO;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.Getter;

@Getter
public class Comments implements Iterable<Comment> {

  private List<Comment> comments;

  private Comments(List<Comment> comments) {
    this.comments = comments;
  }


  public static Comments of(List<Comment> comments) {
    return new Comments(comments);
  }

  public static Comments create(CommentsDTO commentsDTO) {

    List<Comment> comments = commentsDTO.stream()
        .map(Comment::of)
        .collect(Collectors.toList());

    return new Comments(comments);
  }


  public boolean isOtherUserExist(User exceptUser) {
    return comments.stream()
        .anyMatch(comment -> !comment.getAuthor().equals(exceptUser));
  }


  @Override
  public Iterator<Comment> iterator() {
    return comments.iterator();
  }


  @Override
  public void forEach(Consumer<? super Comment> action) {
    comments.forEach(action);
  }


  @Override
  public String toString() {
    return "Comments{" +
        "comments=" + comments +
        '}';
  }


  public Stream<Comment> stream() {
    return comments.stream();
  }


  public int size() {
    return comments.size();
  }


  public boolean isEmpty() {
    return comments.isEmpty();
  }

}
