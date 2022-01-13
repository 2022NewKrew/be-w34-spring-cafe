package com.kakao.cafe.repository;

import java.util.List;
import java.util.Optional;

public interface Repository <T, V, K>{
    T save(V dto);
    Optional<T> findByUserId(K userId);
    List<T> findAll();
}
