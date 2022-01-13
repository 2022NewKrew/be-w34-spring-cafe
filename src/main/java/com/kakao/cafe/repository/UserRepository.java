package com.kakao.cafe.repository;

import java.sql.SQLException;
import java.util.List;

public interface UserRepository<T> {
    void save(T t) throws SQLException;
    List<T> findAll();
    T findByUserId(String s);
    T findByName(String s);
    void update(T t);
}
