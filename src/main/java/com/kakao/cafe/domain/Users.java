package com.kakao.cafe.domain;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Stream;

public class Users implements Iterable<User>{

  private final List<User> users;

  private Users() {
    users = new ArrayList<>();
  }

  private Users(List<User> users) {
    this.users = users;
  }

  public static Users of(List<User> users) {
    return new Users(users);
  }

  public static Users createEmpty() {
    return new Users();
  }

  @Override
  public Iterator<User> iterator() {
    return users.iterator();
  }

  @Override
  public void forEach(Consumer<? super User> action) {
    users.forEach(action);
  }

  @Override
  public String toString() {
    return "Users{" +
        "users=" + users +
        '}';
  }

  public Stream<User> stream() {
    return users.stream();
  }

}
