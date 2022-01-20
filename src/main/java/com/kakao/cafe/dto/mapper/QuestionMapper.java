package com.kakao.cafe.dto.mapper;

import com.kakao.cafe.domain.question.Question;
import com.kakao.cafe.dto.question.QuestionResponseDto;
import com.kakao.cafe.dto.question.QuestionSaveDto;
import com.kakao.cafe.dto.question.QuestionUpdateDto;
import com.kakao.cafe.dto.question.QuestionsResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface QuestionMapper {
    QuestionMapper INSTANCE = Mappers.getMapper(QuestionMapper.class);

    QuestionResponseDto toDto(Question question);

    Question toEntityFromSaveDto(QuestionSaveDto questionSaveDto);

    Question toEntityFromUpdateDto(QuestionUpdateDto questionUpdateDto);

    List<QuestionsResponseDto> toDtoList(List<Question> questions);
}
