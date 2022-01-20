package com.kakao.cafe.dto.mapper;

import com.kakao.cafe.domain.answer.Answer;
import com.kakao.cafe.dto.answer.AnswerSaveDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AnswerMapper {
    AnswerMapper INSTANCE = Mappers.getMapper(AnswerMapper.class);

    Answer toEntityFromSaveDto(AnswerSaveDto answerSaveDto);
}
