package com.kakao.cafe.Service;

import com.kakao.cafe.Dto.Login.LoginAuthDto;
import com.kakao.cafe.Dto.Reply.ReplyRequestDto;
import com.kakao.cafe.Exception.NotAuthorizedException;
import com.kakao.cafe.Repository.ReplyDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReplyService {
    private final ReplyDao replyDao;

    public void createReply(int postId, String comment, LoginAuthDto authInfo) {
        ReplyRequestDto reply = new ReplyRequestDto(comment, authInfo.getUserId(), postId);
        replyDao.insert(reply);
    }

    public void deleteById(int replyId, LoginAuthDto authInfo) {
        if (!replyDao.findById(replyId).getWriter().equals(authInfo.getUserId())) {
            throw new NotAuthorizedException("댓글 삭제 권한이 없습니다.");
        }
        replyDao.deleteById(replyId);
    }
}
