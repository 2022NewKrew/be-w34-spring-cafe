package com.kakao.cafe.service;

import com.kakao.cafe.domain.Reply;
import com.kakao.cafe.dto.reply.ReplyDto;
import com.kakao.cafe.repository.reply.ReplyRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReplyService {

    private final ReplyRepository repository;
    private final ModelMapper modelMapper;

    @Autowired
    public ReplyService(ReplyRepository repository, ModelMapper modelMapper) {
        this.repository = repository;
        this.modelMapper = modelMapper;
    }

    public long create(long userId, long articleId, String comments) {
        Reply reply = new Reply(userId, articleId, comments, LocalDateTime.now());
        reply = repository.save(reply);
        return reply.getId();
    }

    public long delete(long id) {
        return repository.delete(id);
    }

    public long deleteByArticleId(long id) {
        return repository.deleteByArticleId(id);
    }

    public List<ReplyDto> findAllReplyByArticleId(long id) {
        return repository.findAllReply(id).stream()
                .map(m -> modelMapper.map(m, ReplyDto.class))
                .collect(Collectors.toList());
    }

    public String findUserNicknameById(long id) {
        return repository.findUserNicknameById(id).orElse("탈퇴한 사용자");
    }
}
