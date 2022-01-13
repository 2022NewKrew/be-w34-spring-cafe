package com.kakao.cafe.common;

import java.util.List;

public interface Repository<T> {
    T fetch(long id);

    List<T> fetchAll();

    long save(T target);
}
