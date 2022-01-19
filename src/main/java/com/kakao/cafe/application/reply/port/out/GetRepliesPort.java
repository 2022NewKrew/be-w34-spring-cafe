package com.kakao.cafe.application.reply.port.out;

import com.kakao.cafe.application.reply.dto.ReplyList;

public interface GetRepliesPort {

    ReplyList getListOfRepliesTheArticle(int articleId);
}
