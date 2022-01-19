package com.kakao.cafe.reply.repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.kakao.cafe.reply.domain.Reply;

@Repository
public class ReplyRepository {
    private static AtomicLong idSequence = new AtomicLong();
    private final static HashMap<Long, Reply> replyDB = new HashMap<>();

    public Reply find(Long id) {
        return replyDB.get(id);
    }

    public ArrayList<Reply>
    findByArticle(Long articleId) {
        return replyDB.values()
                      .stream()
                      .filter(reply -> reply.getArticleId().equals(articleId))
                      .collect(Collectors.toCollection(ArrayList::new));
    }

    public Long persist(ReplyCreateRequestDTO dto) {
        replyDB.put(idSequence.get(),
                    new Reply(idSequence.get(), dto.articleId, dto.authorId, dto.contents, LocalDateTime.now()));
        return idSequence.getAndIncrement();
    }

}
