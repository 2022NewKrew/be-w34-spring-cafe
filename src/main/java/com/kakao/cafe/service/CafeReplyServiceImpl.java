package com.kakao.cafe.service;

import com.kakao.cafe.dao.CafeReplyDao;
import com.kakao.cafe.helper.ReplyHelper;
import com.kakao.cafe.model.Reply;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CafeReplyServiceImpl implements CafeReplyService {

    CafeReplyDao cafeReplyDao;
    public CafeReplyServiceImpl(CafeReplyDao cafeReplyDao) {
        this.cafeReplyDao = cafeReplyDao;
    }

    @Override
    public List<Reply> getReplyList(int postId) {
        return cafeReplyDao.getReplyList(postId);
    }

    @Override
    public boolean submitReply(Reply reply) {
        if(reply != null && ReplyHelper.checkRegexOfReply(reply)) {
            return cafeReplyDao.submitReply(reply);
        }
        return false;
    }

    @Override
    public boolean deleteReply(String userId, int replyId) {
        return cafeReplyDao.deleteReply(userId, replyId);
    }
}
