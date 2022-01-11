package com.kakao.cafe.domain.repository;

public interface BaseRepository<T> {
    void store(T t);
    T retrieve(int id);
    void modify(int id, T t);
    T delete(int id);
}
