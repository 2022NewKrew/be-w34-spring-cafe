package com.kakao.cafe.application.reply;

import com.kakao.cafe.adapter.out.infra.persistence.reply.JdbcReplyRepository;
import com.kakao.cafe.adapter.out.infra.persistence.reply.ReplyAdapter;
import com.kakao.cafe.adapter.out.infra.persistence.reply.ReplyMapper;
import com.kakao.cafe.adapter.out.infra.persistence.reply.ReplyRepository;
import com.kakao.cafe.application.reply.port.in.DeleteReplyUseCase;
import com.kakao.cafe.application.reply.port.in.GetRepliesUseCase;
import com.kakao.cafe.application.reply.port.in.WriteReplyUseCase;
import com.kakao.cafe.application.reply.port.out.DeleteReplyPort;
import com.kakao.cafe.application.reply.port.out.GetRepliesPort;
import com.kakao.cafe.application.reply.port.out.RegisterReplyPort;
import com.kakao.cafe.application.reply.service.DeleteReplyService;
import com.kakao.cafe.application.reply.service.GetRepliesService;
import com.kakao.cafe.application.reply.service.WriteReplyService;
import com.kakao.cafe.domain.article.Reply;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.RowMapper;

@Configuration
public class ReplyConfig {

    public final DataSource dataSource;

    @Autowired
    public ReplyConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    public RowMapper<Reply> replyMapper() {
        return new ReplyMapper();
    }

    @Bean
    public ReplyRepository replyRepository() {
        return new JdbcReplyRepository(dataSource, replyMapper());
    }

    @Bean
    public RegisterReplyPort registerReplyPort() {
        return new ReplyAdapter(replyRepository());
    }

    @Bean
    public GetRepliesPort getRepliesPort() {
        return new ReplyAdapter(replyRepository());
    }

    @Bean
    public DeleteReplyPort deleteReplyPort() {
        return new ReplyAdapter(replyRepository());
    }

    @Bean
    public WriteReplyUseCase writeReplyUseCase() {
        return new WriteReplyService(registerReplyPort());
    }

    @Bean
    public GetRepliesUseCase getRepliesUseCase() {
        return new GetRepliesService(getRepliesPort());
    }

    @Bean
    public DeleteReplyUseCase deleteReplyUseCase() {
        return new DeleteReplyService(deleteReplyPort());
    }
}
