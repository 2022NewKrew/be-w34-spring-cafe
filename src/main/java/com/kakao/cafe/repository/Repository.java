package com.kakao.cafe.repository;

import java.util.Optional;

public interface Repository<T, ID> {
    Optional<T> findById(ID id);

    boolean existsById(ID id);

    T save(T t);

    void delete(T t);

    int countAll();
}
