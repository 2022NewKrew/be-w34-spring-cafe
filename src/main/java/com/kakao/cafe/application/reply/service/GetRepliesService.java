package com.kakao.cafe.application.reply.service;

import com.kakao.cafe.application.reply.dto.Replies;
import com.kakao.cafe.application.reply.port.in.GetRepliesUseCase;
import com.kakao.cafe.application.reply.port.out.GetRepliesPort;

public class GetRepliesService implements GetRepliesUseCase {

    private final GetRepliesPort getRepliesPort;

    public GetRepliesService(GetRepliesPort getRepliesPort) {
        this.getRepliesPort = getRepliesPort;
    }

    @Override
    public Replies getListOfRepliesOfTheArticle(int articleId) {
        return getRepliesPort.getListOfRepliesTheArticle(articleId);
    }
}
