package com.kakao.cafe.replies;

import com.kakao.cafe.articles.ArticleRepository;
import com.kakao.cafe.articles.JdbcArticleRepository;
import com.kakao.cafe.exceptions.NotFoundException;
import com.kakao.cafe.users.JdbcUserRepository;
import com.kakao.cafe.users.UserDto;
import com.kakao.cafe.users.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReplyService {
    private final ReplyRepository replyRepository;
    private final ArticleRepository articleRepository;
    private final UserRepository userRepository;

    public ReplyService(JdbcReplyRepository replyRepository, JdbcArticleRepository articleRepository, JdbcUserRepository userRepository) {
        this.replyRepository = replyRepository;
        this.articleRepository = articleRepository;
        this.userRepository = userRepository;
    }

    public ReplyDto createReply(ReplyCreationRequest request, UserDto me) {
        userRepository.findById(me.getId())
                .orElseThrow(() -> new NotFoundException("유저를 찾을 수 없습니다."));
        articleRepository.findById(request.getArticleId())
                .orElseThrow(() -> new NotFoundException("글을 찾을 수 없습니다"));

        Reply reply = new Reply(request.getContent(), request.getArticleId(), request.getWriterId());
        Reply savedReply = replyRepository.save(reply);

        return ReplyDto.toDto(savedReply);
    }

    public List<ReplyDto> getAllReplyByArticleIdAndStatus(Long articleId, boolean status) {
        articleRepository.findById(articleId)
                .orElseThrow(() -> new NotFoundException("글을 찾을 수 없습니다"));

        List<Reply> replies = replyRepository.findAllByArticleIdAndStatus(articleId, status);

        return replies
                .stream()
                .map(ReplyDto::toDto)
                .collect(Collectors.toList());
    }

    public void deleteReply(Long id) {
        replyRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("댓글을 찾을 수 없습니다."));

        replyRepository.delete(id);
    }
}
