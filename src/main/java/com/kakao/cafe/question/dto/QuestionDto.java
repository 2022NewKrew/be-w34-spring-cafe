package com.kakao.cafe.question.dto;

import com.kakao.cafe.question.Question;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 질문글 리스트에 대한 DTO 입니다.
 *
 * @author jm.hong
 */
@Getter
@Setter
public class QuestionDto {
    private Long id;
    private Long MemberId;
    private String writer;
    private String title;
    private String contents;
    private LocalDateTime createTime;
    private boolean thisIsMine;

    public static List<QuestionDto> of(List<Question> questions, Long memberId) {
        return questions.stream()
                .map(q ->
                        QuestionDto.of(q, memberId)
                )
                .collect(Collectors.toList());
    }

    public static QuestionDto of(Question q, Long memberId) {
        QuestionDto questionDto = new QuestionDto();
        questionDto.setId(q.getId());
        questionDto.setContents(q.getContents());
        questionDto.setMemberId(q.getMemberId());
        questionDto.setTitle(q.getTitle());
        questionDto.setWriter(q.getWriter());
        questionDto.setCreateTime(q.getCreateTime());
        questionDto.setThisIsMine(memberId.equals(q.getMemberId()));
        return questionDto;
    }
}
