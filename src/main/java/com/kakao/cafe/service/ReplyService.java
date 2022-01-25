package com.kakao.cafe.service;

import com.kakao.cafe.dto.ReplyDto;
import com.kakao.cafe.model.Reply;
import com.kakao.cafe.repository.ReplyJdbcRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReplyService {

    private final ReplyJdbcRepository replyJdbcRepository;

    public ReplyService(ReplyJdbcRepository replyJdbcRepository) {
        this.replyJdbcRepository = replyJdbcRepository;
    }

    public void save(ReplyDto replyDto) {
        replyJdbcRepository.save(replyDto.toEntity());
    }

    public List<Reply> findAll() {
        return replyJdbcRepository.findAll();
    }

}
