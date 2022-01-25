package com.kakao.cafe.repository;

import com.kakao.cafe.domain.Reply;
import java.util.List;

public interface ReplyRepository {

    void save(Reply reply);

    List<Reply> findAllByPostId(int postId);

    Reply findReplyById(int id);

    void delete(int id);

    void deleteAllByPostId(int postId);
}
