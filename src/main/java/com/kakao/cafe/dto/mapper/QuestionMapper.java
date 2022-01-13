package com.kakao.cafe.dto.mapper;

import com.kakao.cafe.domain.question.Question;
import com.kakao.cafe.dto.question.QuestionRequestDto;
import com.kakao.cafe.dto.question.QuestionResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import java.util.List;

@Mapper
public interface QuestionMapper {
    QuestionMapper INSTANCE = Mappers.getMapper(QuestionMapper.class);

    QuestionResponseDto toDto(Question question);

    Question toEntity(QuestionRequestDto questionRequestDto);

    List<QuestionResponseDto> toDtoList(List<Question> questions);
}
