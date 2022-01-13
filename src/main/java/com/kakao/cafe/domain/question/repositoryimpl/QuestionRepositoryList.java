package com.kakao.cafe.domain.question.repositoryimpl;

import com.kakao.cafe.domain.question.Question;
import com.kakao.cafe.domain.question.QuestionRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Repository("QuestionRepositoryList")
public class QuestionRepositoryList implements QuestionRepository {
    private List<Question> questions = new ArrayList<Question>();
    private int maxIndex = 0;

    @Override
    public void save(Question question){
        if(question.isNew()){
            questions.add(Question.builder()
                    .id(maxIndex)
                    .title(question.getTitle())
                    .writer(question.getWriter())
                    .contents(question.getContents())
                    .createdAt(LocalDateTime.now())
                    .build());
            maxIndex++;
            return;
        }
        findById(question.getId()).update(question);
    }

    @Override
    public Question findById(int id){
        return questions.stream().filter(question -> question.getId()==id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Question> findAll(){
        return questions;
    }
}
