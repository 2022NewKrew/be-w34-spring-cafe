package com.kakao.cafe.service;

import com.kakao.cafe.domain.Reply;
import com.kakao.cafe.dto.ReplyRequestDTO;
import com.kakao.cafe.dto.ReplyResponseDTO;
import com.kakao.cafe.error.exception.ReplyNotFoundException;
import com.kakao.cafe.repository.ReplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReplyService {
    private final ReplyRepository replyRepository;

    @Transactional
    public void create(ReplyRequestDTO replyRequestDto) {
        replyRepository.save(replyRequestDto);
    }

    @Transactional(readOnly = true)
    public ReplyResponseDTO read(Long id) {
        Reply reply = replyRepository.findById(id).orElseThrow(ReplyNotFoundException::new);
        return mapper(reply);
    }

    @Transactional(readOnly = true)
    public List<Reply> readAll(Long id) {
        return replyRepository.findByArticle(id);
    }

    @Transactional
    public void delete(Long id) {
        replyRepository.findById(id).orElseThrow(ReplyNotFoundException::new);
        replyRepository.delete(id);
    }

    @Transactional
    public void deleteAll(Long id) {
        replyRepository.deleteAll(id);
    }

    private ReplyResponseDTO mapper(Reply reply) {
        return ReplyResponseDTO.builder()
                .id(reply.getId())
                .author(reply.getAuthor())
                .authorName(reply.getAuthorName())
                .content(reply.getContent())
                .createdAt(reply.getCreatedAt())
                .build();
    }
}
