package com.kakao.cafe.dto.question;

import com.kakao.cafe.domain.answer.Answer;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Getter
public class QuestionResponseDto {
    private int id;
    private int userId;
    private List<Answer> answers;
    private String title;
    private String writer;
    private String contents;
    private LocalDateTime createdAt;
}
