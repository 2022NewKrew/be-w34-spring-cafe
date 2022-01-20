package com.kakao.cafe.service;

import com.kakao.cafe.domain.answer.Answer;
import com.kakao.cafe.domain.answer.AnswerRepository;
import com.kakao.cafe.dto.answer.AnswerSaveDto;
import com.kakao.cafe.dto.mapper.AnswerMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AnswerService {
    private final AnswerRepository answerRepository;

    public Answer save(AnswerSaveDto answerSaveDto){
        return answerRepository.save(AnswerMapper.INSTANCE.toEntityFromSaveDto(answerSaveDto));
    }

    public Answer findById(int id){
        return answerRepository.findById(id);
    }

    public void deleteById(int id){
        answerRepository.deleteById(id);
    }
}
