package com.kakao.cafe.service;

import com.kakao.cafe.domain.Question;
import com.kakao.cafe.domain.User;
import com.kakao.cafe.dto.QuestionCreateRequest;
import com.kakao.cafe.dto.QuestionDetailResponse;
import com.kakao.cafe.repository.QuestionRepository;
import com.kakao.cafe.repository.QuestionRepositoryImpl;
import com.kakao.cafe.repository.UserRepository;
import com.kakao.cafe.repository.UserRepositoryImpl;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleService {
    private final QuestionRepository questionRepository = new QuestionRepositoryImpl();
    private final UserRepository userRepository = new UserRepositoryImpl();

    public Long saveQuestion(QuestionCreateRequest questionDTO) {
        User writer = userRepository.findByUserId(questionDTO.getWriter()).orElseThrow(IllegalArgumentException::new);
        Question question = new Question(questionDTO, writer);
        return questionRepository.save(question).getId();
    }

    public List<Question> findAllQuestions(){
        return questionRepository.findAll();
    }
}
