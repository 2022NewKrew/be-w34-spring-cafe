package com.kakao.cafe.service;

import com.kakao.cafe.domain.Question;
import com.kakao.cafe.domain.User;
import com.kakao.cafe.dto.QuestionCreateRequest;
import com.kakao.cafe.dto.QuestionDetailResponse;
import com.kakao.cafe.dto.QuestionListResponse;
import com.kakao.cafe.repository.InMemoryQuestionRepository;
import com.kakao.cafe.repository.InMemoryUserRepository;
import com.kakao.cafe.repository.QuestionRepository;
import com.kakao.cafe.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ArticleService {
    private final QuestionRepository questionRepository ;
    private final UserRepository userRepository ;

    public Long saveQuestion(QuestionCreateRequest questionDTO, LocalDateTime dateTime) {
        User writer = userRepository.findByNickname(questionDTO.getWriter()).orElseThrow(IllegalArgumentException::new);
        Question question = questionDTO.toEntity(writer.getId(), dateTime);
        return questionRepository.save(question).getId();
    }

    public List<QuestionListResponse> findAllQuestions(){
        List<Question> questions = questionRepository.findAll();
        return questions.stream().map(question -> {
            return new QuestionListResponse(question, findWriterNickname(question));
        }).collect(Collectors.toList());
    }

    public QuestionDetailResponse findOneQuestion(Long id) {
        Question question = questionRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        return new QuestionDetailResponse(question, findWriterNickname(question));
    }

    private String findWriterNickname(Question question){
        return userRepository.findById(question.getWriter()).orElseThrow(IllegalArgumentException::new).getNickname();
    }
}
