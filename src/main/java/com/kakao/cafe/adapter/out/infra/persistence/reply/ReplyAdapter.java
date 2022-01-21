package com.kakao.cafe.adapter.out.infra.persistence.reply;

import com.kakao.cafe.application.reply.dto.Replies;
import com.kakao.cafe.application.reply.dto.WriteReplyRequest;
import com.kakao.cafe.application.reply.port.out.DeleteReplyPort;
import com.kakao.cafe.application.reply.port.out.GetRepliesPort;
import com.kakao.cafe.application.reply.port.out.RegisterReplyPort;
import com.kakao.cafe.domain.article.Reply;
import com.kakao.cafe.domain.article.exceptions.IllegalDateException;
import com.kakao.cafe.domain.article.exceptions.IllegalTitleException;
import com.kakao.cafe.domain.article.exceptions.IllegalWriterException;
import com.kakao.cafe.domain.user.exceptions.IllegalUserIdException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class ReplyAdapter implements RegisterReplyPort, GetRepliesPort, DeleteReplyPort {

    private final ReplyRepository replyRepository;

    public ReplyAdapter(ReplyRepository replyRepository) {
        this.replyRepository = replyRepository;
    }

    @Override
    public void registerReply(WriteReplyRequest writeReplyRequest)
        throws IllegalUserIdException, IllegalWriterException, IllegalTitleException, IllegalDateException {
        replyRepository.save(
            new Reply.Builder().articleId(writeReplyRequest.getArticleId())
                               .userId(writeReplyRequest.getUserId())
                               .writer(writeReplyRequest.getWriter())
                               .contents(writeReplyRequest.getContents())
                               .createdAt(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")))
                               .deleted(false)
                               .build()
        );
    }

    @Override
    public Replies getListOfRepliesTheArticle(int articleId) {
        List<Reply> replyList = replyRepository.getAllReplyListByArticleId(articleId);

        return Replies.from(replyList);
    }

    @Override
    public void delete(int id) {
        replyRepository.deleteById(id);
    }

    @Override
    public void deleteAllRepliesInArticle(int articleId) {
        replyRepository.deleteAllRepliesIn(articleId);
    }
}
