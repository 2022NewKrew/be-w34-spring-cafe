package com.kakao.cafe.Service;

import com.kakao.cafe.Dto.Login.LoginAuthDto;
import com.kakao.cafe.Dto.Reply.ReplyRequestDto;
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
}
