package com.kakao.cafe.repository;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


public interface RepositoryInterface<T> {
    T save(T t);
    Optional<T> findById(Long id);
    Optional<T> findByName(String name);
    List<T> findAll();
    T update(T t);
}
