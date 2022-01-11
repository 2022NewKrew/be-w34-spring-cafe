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
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ArticleService {
    private final QuestionRepository questionRepository = new InMemoryQuestionRepository();
    private final UserRepository userRepository = new InMemoryUserRepository();

    public Long saveQuestion(QuestionCreateRequest questionDTO) {
        User writer = userRepository.findByNickname(questionDTO.getWriter()).orElseThrow(IllegalArgumentException::new);
        Question question = new Question(questionDTO, writer);
        return questionRepository.save(question).getId();
    }

    public List<QuestionListResponse> findAllQuestions(){
        List<Question> questions = questionRepository.findAll();
        return questions.stream().map(QuestionListResponse::new).collect(Collectors.toList());
    }

    public QuestionDetailResponse findOneQuestion(Long id) {
        Question question = questionRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        return new QuestionDetailResponse(question);
    }
}
