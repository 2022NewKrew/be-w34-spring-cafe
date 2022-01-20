package com.kakao.cafe.application.reply.port.in;

import com.kakao.cafe.application.reply.dto.ReplyList;

public interface GetRepliesUseCase {

    ReplyList getListOfRepliesOfTheArticle(int articleId);
}
