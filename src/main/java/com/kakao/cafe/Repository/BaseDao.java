package com.kakao.cafe.Repository;

import java.util.List;

public interface BaseDao<ResponseT, Long, RequestT> {
    List<ResponseT> findAll();

    ResponseT findById(Long id);

    void save(RequestT entity);
}
