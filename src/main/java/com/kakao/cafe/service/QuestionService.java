package com.kakao.cafe.service;

import com.kakao.cafe.domain.question.Question;
import com.kakao.cafe.domain.question.QuestionRepository;
import com.kakao.cafe.dto.mapper.QuestionMapper;
import com.kakao.cafe.dto.question.QuestionResponseDto;
import com.kakao.cafe.dto.question.QuestionSaveDto;
import com.kakao.cafe.dto.question.QuestionUpdateDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {
    @Autowired
    @Qualifier("QuestionRepositoryList")
    private QuestionRepository questionRepository;

    public void save(QuestionSaveDto questionSaveDto){
        questionRepository.save(QuestionMapper.INSTANCE.toEntityFromSaveDto(questionSaveDto));
    }

    public void update(int id, QuestionUpdateDto questionUpdateDto){
        Question question = questionRepository.findById(id);
        Question questionFromUpdateDto = QuestionMapper.INSTANCE.toEntityFromUpdateDto(questionUpdateDto);
        question.changeTitle(questionFromUpdateDto.getTitle());
        question.changeContents(questionFromUpdateDto.getContents());
        questionRepository.save(question);
    }

    public void deleteById(int id){
        questionRepository.deleteById(id);
    }

    public List<QuestionResponseDto> findAll(){
        return QuestionMapper.INSTANCE.toDtoList(questionRepository.findAll());
    }

    public QuestionResponseDto findById(int id){
        return QuestionMapper.INSTANCE.toDto(questionRepository.findById(id));
    }
}
