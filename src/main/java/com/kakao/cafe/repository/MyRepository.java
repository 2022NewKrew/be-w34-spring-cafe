package com.kakao.cafe.repository;

import java.util.List;
import java.util.Optional;

public interface MyRepository<T, I> {

    Optional<T> findById(I id);

    List<T> findAll();

    T save(T entity);

    T update(T entity);

}
