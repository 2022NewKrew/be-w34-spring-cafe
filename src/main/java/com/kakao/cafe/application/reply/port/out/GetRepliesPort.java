package com.kakao.cafe.application.reply.port.out;

import com.kakao.cafe.application.reply.dto.Replies;

public interface GetRepliesPort {

    Replies getListOfRepliesTheArticle(int articleId);
}
