package com.kakao.cafe.reply.repository;

import com.kakao.cafe.reply.domain.Reply;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.Collectors;

@Repository
public class ReplyRepository {
    private static Long idSequence = 0L;
    private final static HashMap<Long, Reply> replyDB = new HashMap<>();

    public Reply find(Long id) {
        return replyDB.get(id);
    }

    public ArrayList<Reply>
    findByArticle(Long articleId) {
        return replyDB.values().stream().filter(reply -> reply.getArticleId().equals(articleId)).collect(Collectors.toCollection(ArrayList::new));
    }

    public Long persist(CreateReplyRequestDTO dto) {
        replyDB.put(idSequence, new Reply(idSequence, dto.articleId, dto.authorId, dto.contents, LocalDateTime.now()));
        idSequence += 1;
        return idSequence - 1;
    }

}
