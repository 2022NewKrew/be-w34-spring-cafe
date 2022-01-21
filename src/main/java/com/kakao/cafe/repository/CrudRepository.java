package com.kakao.cafe.repository;

import com.kakao.cafe.domain.Article;

import java.util.Optional;

public interface CrudRepository<T, ID> {

    T save(T entity);

    default T update(T entity) {
        return null;
    }

    Optional<T> findById(ID id);

    default Iterable<T> findAll() {
        return null;
    }

    boolean delete(T entity);
}
