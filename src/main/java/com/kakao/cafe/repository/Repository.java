package com.kakao.cafe.repository;

import java.util.List;
import java.util.Optional;

public interface Repository<T, I> {

    void create(T entity);

    List<T> ReadAll();

    Optional<T> ReadById(I id);

}
