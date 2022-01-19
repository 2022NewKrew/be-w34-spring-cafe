package com.kakao.cafe.service.article;

import com.kakao.cafe.domain.article.Comment;
import com.kakao.cafe.domain.article.Text;
import com.kakao.cafe.domain.article.Time;
import com.kakao.cafe.domain.member.Member;
import com.kakao.cafe.repository.comment.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Transactional
public class CommentServiceV1 implements CommentService {

    private final CommentRepository commentRepository;
    private final ArticleService articleService;

    @Override
    public Comment postComment(Long articleId, Member loginMember, Text text) {
        Comment comment = Comment.createComment(articleService.inquireOneArticle(articleId), Time.now(), text, loginMember);
        return commentRepository.save(comment);
    }
}
