package com.kakao.cafe.service.article;

import com.kakao.cafe.domain.article.Comment;
import com.kakao.cafe.domain.article.Text;
import com.kakao.cafe.domain.member.Member;

public interface CommentService {

    Comment postComment(Long articleId, Member loginMember, Text text);
}
