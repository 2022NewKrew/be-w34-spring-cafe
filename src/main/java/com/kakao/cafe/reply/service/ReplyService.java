package com.kakao.cafe.reply.service;

import com.kakao.cafe.member.service.MemberService;
import com.kakao.cafe.reply.domain.Reply;
import com.kakao.cafe.reply.dto.ReplyRequestDTO;
import com.kakao.cafe.reply.dto.ReplyResponseDTO;
import com.kakao.cafe.reply.repository.ReplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReplyService {

    private final ReplyRepository replyRepository;
    private final MemberService memberService;

    public ReplyResponseDTO writeReply(Long articleId, Long sessionId, ReplyRequestDTO replyRequestDTO) {
        Reply reply = replyRequestDTO.toReply(articleId, sessionId, LocalDateTime.now());
        return new ReplyResponseDTO(replyRepository.save(reply), memberService.findOne(sessionId).getNickname());
    }

    public List<ReplyResponseDTO> findByArticle(Long articleId) {
        return replyRepository.findByArticle(articleId).stream()
                .map(reply -> new ReplyResponseDTO(reply, memberService.findOne(reply.getMemberId()).getNickname()))
                .collect(Collectors.toList());
    }

    public void delete(Long id, Long sessionId) {
        replyRepository.delete(id);
    }
}
