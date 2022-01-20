package com.kakao.cafe.reply;

import com.kakao.cafe.common.exception.BaseException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
public class ReplyServiceImpl implements ReplyService {

    private final ReplyRepository replyRepository;

    @Override
    public Long save(Reply reply) throws SQLException {
        return replyRepository.save(reply);
    }

    @Override
    public boolean deleteOne(Long id, Long memberId, Long questionId) throws BaseException {
        Reply origin = replyRepository.findOne(id);

        if (!memberId.equals(origin.getMemberId())) {
            throw new BaseException("로그인 사용자와 게시글 작성자가 다릅니다.");
        }

        return replyRepository.deleteOne(id);
    }

    @Override
    public List<Reply> findAllAsQuestionId(Long questionId) {
        return replyRepository.findAllAsQuestionId(questionId);
    }
}
