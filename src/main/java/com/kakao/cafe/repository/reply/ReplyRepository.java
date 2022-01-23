package com.kakao.cafe.repository.reply;

import com.kakao.cafe.entity.ReplyEntity;
import com.kakao.cafe.repository.CrudRepository;

import java.util.List;

public interface ReplyRepository extends CrudRepository<ReplyEntity, Long> {
    void deleteById(Long primaryKey);

    List<ReplyEntity> findByReplyId(Long articleId);
}
