package com.kakao.cafe.reply.controller;

import com.kakao.cafe.member.dto.MemberResponseDTO;
import com.kakao.cafe.reply.dto.ReplyRequestDTO;
import com.kakao.cafe.reply.dto.ReplyResponseDTO;
import com.kakao.cafe.reply.service.ReplyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ReplyController {

    private final ReplyService replyService;

    @PostMapping("/articles/{articleId}/reply")
    public ResponseEntity<ReplyResponseDTO> postReply(@PathVariable Long articleId, HttpSession session, ReplyRequestDTO replyRequestDTO) {
        MemberResponseDTO loginMemberDTO = (MemberResponseDTO) session.getAttribute("loginMemberDTO");
        ReplyResponseDTO replyResponseDTO = replyService.writeReply(articleId, loginMemberDTO.getId(), replyRequestDTO);
        log.info("replyResponseDTO : {}", replyResponseDTO);
        return ResponseEntity.ok().body(replyResponseDTO);
    }

    @DeleteMapping("/articles/{articleId}/reply/{id}")
    public ResponseEntity<String> deleteReply(@PathVariable Long articleId, @PathVariable Long id, HttpSession session) {
        MemberResponseDTO loginMemberDTO = (MemberResponseDTO) session.getAttribute("loginMemberDTO");
        replyService.delete(id, loginMemberDTO.getId());
        return ResponseEntity.ok().body("Delete Success");
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleReplyException(Exception e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
