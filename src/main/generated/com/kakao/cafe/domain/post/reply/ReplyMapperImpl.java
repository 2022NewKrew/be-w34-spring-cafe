package com.kakao.cafe.domain.post.reply;

import com.kakao.cafe.interfaces.common.ReplyDto;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-01-21T18:01:58+0900",
    comments = "version: 1.5.0.Beta2, compiler: javac, environment: Java 11.0.13 (Eclipse Adoptium)"
)
@Component
public class ReplyMapperImpl implements ReplyMapper {

    @Override
    public Reply toEntity(ReplyDto replyDto) {
        if ( replyDto == null ) {
            return null;
        }

        Reply.ReplyBuilder reply = Reply.builder();

        reply.writer( replyDto.getWriter() );
        reply.body( replyDto.getBody() );

        return reply.build();
    }
}
