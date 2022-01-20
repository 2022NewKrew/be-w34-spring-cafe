package com.kakao.cafe.service;

import com.kakao.cafe.dao.CafeReplyDao;
import com.kakao.cafe.helper.ReplyHelper;
import com.kakao.cafe.model.Reply;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CafeReplyServiceImpl implements CafeReplyService {

    CafeReplyDao cafeCommentDao;
    public CafeReplyServiceImpl(CafeReplyDao cafeCommentDao) {
        this.cafeCommentDao = cafeCommentDao;
    }

    @Override
    public List<Reply> getReplyList(int postId) {
        return cafeCommentDao.getReplyList(postId);
    }

    @Override
    public boolean submitReply(Reply reply) {
        if(reply != null && ReplyHelper.checkRegexOfReply(reply)) {
            return cafeCommentDao.submitReply(reply);
        }
        return false;
    }

    @Override
    public boolean deleteReply(String userId, int replyId) {
        return cafeCommentDao.deleteReply(userId, replyId);
    }
}
