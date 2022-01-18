package com.kakao.cafe.service;

import java.util.List;
import java.util.Optional;

public interface Service <T, U, V>{
    V join(U dto);
    List<T> findAll();
    Optional<T> findOne(V id);
    void delete(V id);
}
