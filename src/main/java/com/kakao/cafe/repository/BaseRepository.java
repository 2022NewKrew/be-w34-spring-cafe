package com.kakao.cafe.repository;

import java.util.List;
import java.util.Optional;

public interface BaseRepository<T> {

    void save(T t);
    List<T> findAll();
    Optional<T> findById(Long id);
    default void deleteById(Long id) {};
}
