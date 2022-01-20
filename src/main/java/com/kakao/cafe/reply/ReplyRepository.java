package com.kakao.cafe.reply;

import java.util.List;
import java.util.Optional;

public interface ReplyRepository {

    void save(Reply reply);

    default void update(Reply reply) {}

    default void delete(Reply reply) {}

    default Optional<Reply> findBySeq(long seq) { return Optional.empty(); }

    default  Optional<List<Reply>> findByArticleSeq(long articleSeq) { return Optional.empty(); }

    List<Reply> findAll();

}
