package com.kakao.cafe.service;

import com.kakao.cafe.domain.question.Questions;
import com.kakao.cafe.dto.QuestionRequestDto;
import com.kakao.cafe.dto.QuestionResponseDto;
import com.kakao.cafe.dto.mapper.QuestionMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {
    //DB생성 전까지 questions 변수에 임시 저장
    private Questions questions = new Questions();

    public void save(QuestionRequestDto questionRequestDto){
        questions.addQuestion(QuestionMapper.INSTANCE.toEntity(questionRequestDto));
    }

    public List<QuestionResponseDto> findAll(){
        return QuestionMapper.INSTANCE.toDtoList(questions.getQuestions());
    }

    public QuestionResponseDto findbyId(int id){
        return QuestionMapper.INSTANCE.toDto(questions.findById(id));
    }
}
