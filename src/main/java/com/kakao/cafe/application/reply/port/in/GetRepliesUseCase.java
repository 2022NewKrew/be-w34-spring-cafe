package com.kakao.cafe.application.reply.port.in;

import com.kakao.cafe.application.reply.dto.Replies;

public interface GetRepliesUseCase {

    Replies getListOfRepliesOfTheArticle(int articleId);
}
