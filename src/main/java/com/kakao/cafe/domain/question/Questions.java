package com.kakao.cafe.domain.question;

import com.kakao.cafe.dto.QuestionSaveDto;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Questions {
    private List<Question> questions = new ArrayList<Question>();
    private int maxIndex = 0;

    public void addQuestion(QuestionSaveDto QuestionSaveDto){
        questions.add(new Question(maxIndex, QuestionSaveDto));
        maxIndex++;
    }

    public Question findById(int id){
        return questions.stream().filter(question -> question.getId()==id)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException());
    }
}
