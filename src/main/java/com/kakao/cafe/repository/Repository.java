package com.kakao.cafe.repository;

import java.util.List;
import java.util.Optional;

public interface Repository<T, S> {

    void create(T entity);

    List<T> readAll();

    Optional<T> readById(S id);

}
