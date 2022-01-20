package com.kakao.cafe.repository;

import com.kakao.cafe.domain.Reply;

import java.sql.SQLException;
import java.util.List;
import java.util.NoSuchElementException;

public interface ReplyRepository {
    int save(Reply reply) throws SQLException;
    Reply findById(int id) throws NoSuchElementException;
    List<Reply> findByAid(int aid);
    void delete(int id);
}
