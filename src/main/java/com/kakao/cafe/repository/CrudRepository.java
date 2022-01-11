package com.kakao.cafe.repository;

import java.util.List;

public interface CrudRepository<T, ID> {
    <S extends T> S save(S entity);

    T findOne(ID primaryKey);

    List<T> findAll();

    Long count();

    void delete(T entity);

    boolean exists(ID primaryKey);
}
