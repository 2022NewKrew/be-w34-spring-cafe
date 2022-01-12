package com.kakao.cafe.domain.question;

import lombok.Getter;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Getter
@Repository
public class QuestionRepositoryNoDB implements QuestionRepository {
    private List<Question> questions = new ArrayList<Question>();
    private int maxIndex = 0;

    public void save(Question question){
        question.setId(maxIndex);
        questions.add(question);
        maxIndex++;
    }

    public Question findById(int id){
        return questions.stream().filter(question -> question.getId()==id)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException());
    }

    public List<Question> findAll(){
        return questions;
    }
}
