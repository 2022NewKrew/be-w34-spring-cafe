package com.kakao.cafe.domain.question;

import com.kakao.cafe.domain.answer.Answer;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
public class Question {
    @Builder.Default
    private int id = -1;
    private int userId;
    private List<Answer> answers;
    private String title;
    private String writer;
    private String contents;
    private LocalDateTime createdAt;

    public void changeTitle(String title){
        this.title = title;
    }

    public void changeContents(String contents){
        this.contents = contents;
    }

    public Boolean isNew(){
        return id == -1;
    }
}
