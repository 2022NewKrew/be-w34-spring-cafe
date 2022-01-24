package com.kakao.cafe.service.reply;

import com.kakao.cafe.dao.reply.ReplyDao;
import com.kakao.cafe.exception.IllegalPermissionException;
import com.kakao.cafe.model.reply.Reply;
import com.kakao.cafe.model.reply.ReplyFactory;
import com.kakao.cafe.service.reply.dto.ReplyCreateDto;
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

    public void checkPermission(int replyId, String loginUserId) {
        Reply reply = replyDao.findReplyById(replyId)
                .orElseThrow(() -> new IllegalArgumentException("찾는 댓글이 존재하지 않습니다."));

        if (!reply.getUserId().getValue().equals(loginUserId)) {
            throw new IllegalPermissionException("본인의 댓글만 삭제할 수 있습니다.");
        }
    }
}
