package com.kakao.cafe.service;

import com.kakao.cafe.domain.question.Question;
import com.kakao.cafe.domain.question.Questions;
import com.kakao.cafe.dto.QuestionSaveDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {
    //DB생성 전까지 questions 변수에 임시 저장
    private Questions questions = new Questions();

    public void save(QuestionSaveDto questionSaveDto){
        questions.addQuestion(questionSaveDto);
    }

    public List<Question> findAll(){
        return questions.getQuestions();
    }

    public Question findbyId(int id){
        return questions.findById(id);
    }
}
