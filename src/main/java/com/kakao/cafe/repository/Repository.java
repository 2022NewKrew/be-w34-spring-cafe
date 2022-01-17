package com.kakao.cafe.repository;

import java.util.List;
import java.util.Optional;

public interface Repository<T, I> {

    void createUser(T entity);

    List<T> readUsers();

    Optional<T> readUser(I id);

}
