package com.kakao.cafe.service;

import com.kakao.cafe.domain.Question;
import com.kakao.cafe.domain.User;
import com.kakao.cafe.dto.QuestionCreateRequest;
import com.kakao.cafe.dto.QuestionDetailResponse;
import com.kakao.cafe.dto.QuestionListResponse;
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

    public void saveQuestion(QuestionCreateRequest questionDTO, LocalDateTime dateTime) {
        User writer = userRepository.findByNickname(questionDTO.getWriter()).orElseThrow(IllegalArgumentException::new);
        Question question = questionDTO.toEntity(writer.getId(), dateTime);
        questionRepository.save(question);
    }

    public List<QuestionListResponse> findAllQuestions(){
        return questionRepository.findAllAndWriterNickname();
    }

    public QuestionDetailResponse findOneQuestion(Long id) {
        return questionRepository.findDetailById(id).orElseThrow(IllegalArgumentException::new);
    }

}
