package com.kakao.cafe.repository;

import java.util.List;

public interface CrudInterface <T, ID>{
    public T save(T entity);
    public T update(T entity);
    public T findById(ID id);
    public List<T> findAll();
}
