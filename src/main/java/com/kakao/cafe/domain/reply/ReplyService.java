package com.kakao.cafe.domain.reply;

import com.kakao.cafe.core.exception.IsNotAuthorOfThisArticle;
import com.kakao.cafe.core.exception.IsNotAuthorOfThisComment;
import com.kakao.cafe.domain.article.ArticleRepository;
import com.kakao.cafe.domain.reply.dto.ReplyCreateForm;
import com.kakao.cafe.domain.reply.dto.ReplyResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReplyService {

    private final ReplyJdbcRepository replyRepository;
    private final ArticleRepository articleRepository;

    public void save(ReplyCreateForm replyCreateForm) {
        replyRepository.save(replyCreateForm);
        //매번 join 연산으로 댓글 갯수 가져오는 것에 비해 댓글 달때마다 쿼리 2번 나가는게 성능이 낫지 않을까 하는 판단
        articleRepository.incrementNumOfComment(replyCreateForm.getArticleId());
    }

    public List<ReplyResponse> getComments(Long articleId) {
        return replyRepository.getAllByArticleId(articleId);
    }

    public void delete(Long id, Long userId) throws IsNotAuthorOfThisComment {
        authorityCheck(id, userId);
        replyRepository.delete(id);
    }


    public void update(Long id, String updateContent, Long userId) throws IsNotAuthorOfThisComment {
        authorityCheck(id, userId);
        replyRepository.update(id, updateContent);
    }

    private void authorityCheck(Long id, Long userId) throws IsNotAuthorOfThisComment{
        if(!replyRepository.checkAuthor(id, userId)) {
            throw new IsNotAuthorOfThisComment();
        }
    }

}
