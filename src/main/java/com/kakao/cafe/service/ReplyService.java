package com.kakao.cafe.service;

import com.kakao.cafe.dao.ReplyDao;
import com.kakao.cafe.exception.IncorrectUserException;
import com.kakao.cafe.util.ErrorUtil;
import com.kakao.cafe.vo.Reply;
import com.kakao.cafe.vo.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReplyService {

    private final ReplyDao replyDao;

    public ReplyService(ReplyDao replyDao) {
        this.replyDao = replyDao;
    }

    public List<Reply> getReplys(int articleId) {
        return replyDao.getReplys(articleId);
    }

    public void addReply(String writer, String contents, int articleId) {
        Reply reply = new Reply(writer, contents, articleId);
        replyDao.addReply(reply);
    }

    public Reply getReply(int id) {
        return replyDao.getReply(id);
    }

    public void updateReply(String writer, String contents, int articleId, int id, User loginUser) throws IncorrectUserException {
        if(!ErrorUtil.checkSameString(writer, loginUser.getUserId()))
            throw new IncorrectUserException();
        Reply reply = new Reply(writer, contents, articleId, id);
        replyDao.updateReply(reply);
    }

    public void deleteReply(int id, User loginUser) throws IncorrectUserException {
        Reply reply = getReply(id);
        if(!ErrorUtil.checkSameString(loginUser.getUserId(), reply.getWriter()))
            throw new IncorrectUserException();
        replyDao.deleteReply(id);
    }

}
