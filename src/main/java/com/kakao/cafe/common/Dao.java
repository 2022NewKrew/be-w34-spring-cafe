package com.kakao.cafe.common;

import java.util.List;

public interface Dao<T> {
    T fetch(long id);

    List<T> fetchAll();

    long save(T target);
}
