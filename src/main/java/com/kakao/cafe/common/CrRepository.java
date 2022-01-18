package com.kakao.cafe.common;

import java.util.List;

public interface CrRepository<T> {
    long save(T target);

    T fetch(long id);

    List<T> fetchAll();
}
