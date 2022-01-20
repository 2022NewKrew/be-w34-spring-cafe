package com.kakao.cafe.application.reply.port.out;

import com.kakao.cafe.application.reply.dto.WriteReplyRequest;
import com.kakao.cafe.domain.article.exceptions.IllegalDateException;
import com.kakao.cafe.domain.article.exceptions.IllegalTitleException;
import com.kakao.cafe.domain.article.exceptions.IllegalWriterException;
import com.kakao.cafe.domain.user.exceptions.IllegalUserIdException;

public interface RegisterReplyPort {

    void registerReply(WriteReplyRequest writeReplyRequest)
        throws IllegalUserIdException, IllegalWriterException, IllegalTitleException, IllegalDateException;
}
