package com.kakao.cafe.application.reply.service;

import com.kakao.cafe.application.reply.dto.WriteReplyRequest;
import com.kakao.cafe.application.reply.port.in.WriteReplyUseCase;
import com.kakao.cafe.application.reply.port.out.RegisterReplyPort;
import com.kakao.cafe.domain.article.exceptions.IllegalDateException;
import com.kakao.cafe.domain.article.exceptions.IllegalTitleException;
import com.kakao.cafe.domain.article.exceptions.IllegalWriterException;
import com.kakao.cafe.domain.user.exceptions.IllegalUserIdException;

public class WriteReplyService implements WriteReplyUseCase {

    private final RegisterReplyPort registerReplyPort;

    public WriteReplyService(RegisterReplyPort registerReplyPort) {
        this.registerReplyPort = registerReplyPort;
    }

    @Override
    public void writeReply(WriteReplyRequest writeReplyRequest)
        throws IllegalUserIdException, IllegalWriterException, IllegalTitleException, IllegalDateException {
        registerReplyPort.registerReply(writeReplyRequest);
    }
}
