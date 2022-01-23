package com.kakao.cafe.service;

import com.kakao.cafe.domain.reply.Reply;
import com.kakao.cafe.domain.reply.ReplyResponseDto;
import com.kakao.cafe.domain.reply.ReplySaveDto;
import com.kakao.cafe.domain.user.User;
import com.kakao.cafe.exception.ForbiddenException;
import com.kakao.cafe.exception.NotFoundException;
import com.kakao.cafe.repository.ReplyRepository;
import com.kakao.cafe.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReplyService {
    private final ReplyRepository replyRepository;
    private final UserRepository userRepository;

    @Autowired
    public ReplyService(ReplyRepository replyRepository, UserRepository userRepository) {
        this.replyRepository = replyRepository;
        this.userRepository = userRepository;
    }

    public void save(ReplySaveDto dto) {
        Reply reply = new Reply();
        reply.setUserId(dto.getUserId());
        reply.setArticleId(dto.getArticleId());
        reply.setContent(dto.getContent());
        replyRepository.save(reply);
    }

    public List<ReplyResponseDto> findAllByArticle(Long articleId) {
        return replyRepository.findAllByArticle(articleId).stream()
                .map(reply -> {
                    User user = userRepository.findById(reply.getUserId())
                            .orElse(null);
                    return new ReplyResponseDto(reply, user);
                })
                .collect(Collectors.toList());
    }

    public void validateUserAndDeleteById(Long id, Long sessionUserId) {
        Reply reply = replyRepository.findById(id)
                        .orElseThrow(() -> new NotFoundException("해당 아이디의 댓글이 없습니다."));
        validateUserEqualsToSessionUser(reply.getUserId(), sessionUserId);
        replyRepository.deleteById(id);
    }

    private void validateUserEqualsToSessionUser(Long replyUserId, Long sessionUserId) {
        if (!replyUserId.equals(sessionUserId)) {
            throw new ForbiddenException("댓글 유저와 로그인한 유저가 다릅니다.");
        }
    }
}
