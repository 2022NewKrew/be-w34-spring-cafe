package com.kakao.cafe.repository;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RepositoryInterface<T> {
    void save(T t);
    Optional<T> findById(Long id);
    Optional<T> findByName(String name);
    List<T> findAll();
}
