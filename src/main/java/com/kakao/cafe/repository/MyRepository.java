package com.kakao.cafe.repository;

import java.util.List;
import java.util.Optional;

public interface MyRepository<T, I> {

    default Optional<T> findById(I id) {
        return Optional.empty();
    }

    default List<T> findAll() {
        return null;
    }

    default void save(T entity) {}

    default void update(T entity) {}

    default void delete(I id) {}

}
