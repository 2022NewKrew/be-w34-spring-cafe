package com.kakao.cafe.question;

import com.kakao.cafe.common.auth.Auth;
import com.kakao.cafe.common.exception.BaseException;
import com.kakao.cafe.reply.Reply;
import com.kakao.cafe.reply.ReplyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;

/**
 * QuestionService 의 구현체입니다.
 *
 * @author jm.hong
 */
@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class QuestionServiceImpl implements QuestionService {

    private final QuestionRepository questionRepository;
    private final ReplyRepository replyRepository;

    /**
     * 질문글을 저장합니다. 저장시에 현제 시스템 시간정보를 저장합니다.
     *
     * @param question 질문글에대한 엔티티
     * @return key 값에 해당하는 질문글을 반환
     */
    @Override
    public Long save(Question question) throws SQLException {
        return questionRepository.save(question);
    }

    @Override
    public Question findOne(Long id) {
        return questionRepository.findOne(id);
    }

    @Override
    public List<Question> findAll() {
        return questionRepository.findAll();
    }

    @Override
    @Auth(role = Auth.Role.ADMIN)
    public boolean deleteOneWidthAdmin(Long id) {
        return questionRepository.deleteOne(id);
    }

    @Override
    public boolean deleteOne(Long id, Long memberId) throws BaseException {

        checkAuthQuestion(id, memberId);

        checkReplyStatus(id, memberId);

        replyRepository.deleteAsQuestionId(id);

        return questionRepository.deleteOne(id);
    }

    private void checkReplyStatus(Long id, Long memberId) throws BaseException {

        List<Reply> replies = replyRepository.findAllAsQuestionId(id);

        for (Reply reply : replies) {
            if (!reply.getMemberId().equals(memberId)) {
                throw new BaseException("현재 게시글에 댓글이 달려 있습니다.");
            }
        }
    }

    private void checkAuthQuestion(Long id, Long memberId) throws BaseException {

        Question origin = questionRepository.findOne(id);

        if (!origin.getMemberId().equals(memberId)) {
            throw new BaseException("권한이 없는 사용자는 삭제 할 수 없습니다.");
        }
    }

    @Override
    public boolean updateOne(Question question) throws BaseException {

        Long id = question.getId();
        Question origin = questionRepository.findOne(id);

        if (!origin.getMemberId().equals(question.getMemberId())) {
            throw new BaseException("권한이 없는 사용자는 수정을 할 수 없습니다.");
        }

        return questionRepository.updateOne(question);
    }

    @Override
    public List<Question> findPage(int currentPage, int pageSize) throws BaseException {

        int questionAllCount = questionRepository.totalCount();

        validationFindPage(currentPage, pageSize, questionAllCount);

        return questionRepository.findPage(currentPage, pageSize);
    }

    @Override
    public int findEndPage(int pageSize) {

        int questionAllCount = questionRepository.totalCount();

        return (questionAllCount - 1) / pageSize + 1;
    }

    private void validationFindPage(int currentPage, int pageSize, int questionAllCount) throws BaseException {
        int endPage = questionAllCount / pageSize + 1;
        validationCurrentPage(currentPage, endPage);
        validationPageSize(pageSize);
    }

    private void validationPageSize(int pageSize) throws BaseException {
        if (pageSize < 15 || pageSize > 100) {
            throw new BaseException("pageSize는 15미만 100초과 할 수 없습니다.");
        }
    }

    private void validationCurrentPage(int currentPage, int endPage) throws BaseException {
        if (currentPage < 1) {
            throw new BaseException("currentPage는 1이상이여야 합니다.");
        }

        if (currentPage > endPage) {
            throw new BaseException(String.format("currentPage는 %d 를 초과 할 수 없습니다.", endPage));
        }

    }
}
