package com.kakao.cafe.repository;

import com.kakao.cafe.domain.Reply;

import java.sql.SQLException;
import java.util.List;

public interface ReplyRepository {
    int save(Reply reply) throws SQLException;
    List<Reply> findByAid(int aid);
    void delete(int id);
}
