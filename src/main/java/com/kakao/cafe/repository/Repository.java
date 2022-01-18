package com.kakao.cafe.repository;

import java.util.List;
import java.util.Optional;

public interface Repository <T, U, V>{
    T save(U dto);
    Optional<T> findById(V id);
    List<T> findAll();
    void delete(V id);
    void update(U dto);
}
