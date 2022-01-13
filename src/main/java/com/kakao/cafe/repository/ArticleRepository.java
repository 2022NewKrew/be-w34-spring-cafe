package com.kakao.cafe.repository;

import java.sql.SQLException;
import java.util.List;

public interface ArticleRepository<T> {
    void save(T t) throws SQLException;
    List<T> findAll();
    T findById(int i);
}
