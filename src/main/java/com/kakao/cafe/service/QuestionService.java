package com.kakao.cafe.service;

import com.kakao.cafe.domain.Question;
import com.kakao.cafe.dto.QuestionCreateRequest;
import com.kakao.cafe.dto.QuestionDetailResponse;
import com.kakao.cafe.dto.QuestionListResponse;
import com.kakao.cafe.dto.QuestionUpdateRequest;
import com.kakao.cafe.repository.QuestionRepository;
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

}
