package com.kakao.cafe.service;

import com.kakao.cafe.domain.answer.Answer;
import com.kakao.cafe.domain.answer.AnswerRepository;
import com.kakao.cafe.domain.question.Question;
import com.kakao.cafe.domain.question.QuestionRepository;
import com.kakao.cafe.dto.mapper.QuestionMapper;
import com.kakao.cafe.dto.question.QuestionResponseDto;
import com.kakao.cafe.dto.question.QuestionSaveDto;
import com.kakao.cafe.dto.question.QuestionUpdateDto;
import com.kakao.cafe.dto.question.QuestionsResponseDto;
import com.kakao.cafe.exception.NotAuthorizedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {
    @Autowired
    @Qualifier("QuestionRepositoryJdbc")
    private QuestionRepository questionRepository;

    @Autowired
    private AnswerRepository answerRepository;

    public void save(QuestionSaveDto questionSaveDto){
        questionRepository.save(QuestionMapper.INSTANCE.toEntityFromSaveDto(questionSaveDto));
    }

    public void update(int id, QuestionUpdateDto questionUpdateDto){
        Question question = questionRepository.findById(id);
        Question questionFromUpdateDto = QuestionMapper.INSTANCE.toEntityFromUpdateDto(questionUpdateDto);
        question.changeTitle(questionFromUpdateDto.getTitle());
        question.changeContents(questionFromUpdateDto.getContents());
        questionRepository.save(question);
    }

    public void deleteById(int id, int userId){
        deleteAnswersInQuestion(id, userId);
        questionRepository.deleteById(id);
    }

    public List<QuestionsResponseDto> findAll(){
        return QuestionMapper.INSTANCE.toDtoList(questionRepository.findAll());
    }

    public QuestionResponseDto findById(int id){
        return QuestionMapper.INSTANCE.toDto(questionRepository.findById(id));
    }

    private void deleteAnswersInQuestion(int id, int userId){
        List<Answer> answers = questionRepository.findById(id).getAnswers();
        answers.stream().forEach(answer ->{
            if (answer.getUserId()!=userId) throw new NotAuthorizedException("질문글의 답변 중 삭제 권한이 없는 답변이 있습니다.");
            answerRepository.deleteById(answer.getId());
        });
    }
}
