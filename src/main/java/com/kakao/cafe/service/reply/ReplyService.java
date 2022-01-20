package com.kakao.cafe.service.reply;

import com.kakao.cafe.dao.reply.ReplyDao;
import com.kakao.cafe.model.reply.Reply;
import com.kakao.cafe.model.reply.ReplyFactory;
import com.kakao.cafe.service.reply.dto.ReplyCreateDto;
import com.kakao.cafe.service.reply.dto.ReplyDto;
import java.util.List;
import java.util.stream.Collectors;
import javax.naming.NoPermissionException;
import org.springframework.stereotype.Service;

@Service
public class ReplyService {

    private final ReplyDao replyDao;

    public ReplyService(ReplyDao replyDao) {
        this.replyDao = replyDao;
    }

    public void createReply(ReplyCreateDto replyCreateDto) {
        Reply reply = ReplyFactory.getReply(replyCreateDto);
        replyDao.addReply(reply);
    }

    public void deleteReply(int id) {
        replyDao.deleteReply(id);
    }

    public List<ReplyDto> getReplies(int articleId) {
        List<Reply> replies = replyDao.getReplies(articleId);
        return replies
                .stream()
                .map(ReplyDto::new)
                .collect(Collectors.toList());
    }

    public void checkPermission(int replyId, String loginUserId) throws NoPermissionException {
        Reply reply = replyDao.findReplyById(replyId)
                .orElseThrow(() -> new IllegalArgumentException("찾는 댓글이 존재하지 않습니다."));

        if (!reply.getUserId().getValue().equals(loginUserId)) {
            throw new NoPermissionException("본인의 댓글만 삭제할 수 있습니다.");
        }
    }

    public boolean isArticleHasOnlyUserIdReply(int articleId, String userId) {
        List<Reply> replies = replyDao.getReplies(articleId);
        int userIdReplySize = (int) replies
                .stream()
                .filter(reply -> reply.getUserId().getValue().equals(userId))
                .count();
        return userIdReplySize == replies.size();
    }
}
