package com.kakao.cafe.service;

import com.kakao.cafe.domain.question.QuestionRepository;
import com.kakao.cafe.dto.QuestionRequestDto;
import com.kakao.cafe.dto.QuestionResponseDto;
import com.kakao.cafe.dto.mapper.QuestionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {
    //DB생성 전까지 questions 변수에 임시 저장

    @Autowired
    @Qualifier("QuestionRepositoryList")
    private QuestionRepository questionRepository;

    public void save(QuestionRequestDto questionRequestDto){
        questionRepository.save(QuestionMapper.INSTANCE.toEntity(questionRequestDto));
    }

    public List<QuestionResponseDto> findAll(){
        return QuestionMapper.INSTANCE.toDtoList(questionRepository.findAll());
    }

    public QuestionResponseDto findbyId(int id){
        return QuestionMapper.INSTANCE.toDto(questionRepository.findById(id));
    }
}
