package com.kakao.cafe.service;

import com.kakao.cafe.domain.Question;
import com.kakao.cafe.domain.Reply;
import com.kakao.cafe.dto.*;
import com.kakao.cafe.repository.QuestionRepository;
import com.kakao.cafe.repository.ReplyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class QuestionService {
    private final QuestionRepository questionRepository ;
    private final ReplyRepository replyRepository;

    public void saveQuestion(Long userId, QuestionCreateRequest questionDTO, LocalDateTime dateTime) {
        Question question = questionDTO.toEntity(userId, dateTime);
        questionRepository.save(question);
    }

    public List<QuestionListResponse> findAllQuestions(){
        return questionRepository.findAllAndWriterNickname();
    }

    public QuestionDetailResponse findOneQuestion(Long id) {
        return questionRepository.findDetailById(id).orElseThrow(IllegalArgumentException::new);
    }

    public Long updateQuestion(Long userId, QuestionUpdateRequest questionDTO){
        Question updateQuestion = questionDTO.toEntity(userId);
        return questionRepository.update(updateQuestion);
    }

    public void deleteQuestion(Long id, Long userId) {
        //TODO 삭제가능한지 확인 -> 작성자가 맞는지 ? (추후에는 댓글도 확인)
        Question deleteQuestion = Question.builder()
                        .id(id)
                        .writer(userId).build();
        questionRepository.updateIsDeleted(deleteQuestion);
    }

    public void deleteReply(Long questionId, Long replyId, Long userId){
        Reply reply = Reply.builder()
                .id(replyId)
                .questionId(questionId)
                .userId(userId)
                .build();
        replyRepository.updateIsDeleted(reply);

    }

    public void saveReply(Long userId, ReplyCreateRequest replyDTO){
        Reply reply = replyDTO.toEntity(userId);
        replyRepository.save(reply);
    }
}
