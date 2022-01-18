package com.kakao.cafe.post.adapter.out.persistence;

import com.kakao.cafe.post.application.port.out.DeleteQuestionPostPort;
import com.kakao.cafe.post.application.port.out.LoadQuestionPostPort;
import com.kakao.cafe.post.application.port.out.SaveQuestionPostPort;
import com.kakao.cafe.post.application.port.out.UpdateQuestionPostPort;
import com.kakao.cafe.post.domain.QuestionPost;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class QuestionPostPersistenceAdapter implements
        LoadQuestionPostPort,
        SaveQuestionPostPort,
        UpdateQuestionPostPort,
        DeleteQuestionPostPort {

    private final QuestionPostRepository questionPostRepository;

    public QuestionPostPersistenceAdapter(@Qualifier("jdbc-question-db") QuestionPostRepository questionPostRepository) {
        this.questionPostRepository = questionPostRepository;
    }

    @Override
    public List<QuestionPost> findAll() {
        return questionPostRepository.findAll();
    }

    @Override
    public Optional<QuestionPost> findById(Long id) {
        return questionPostRepository.findById(id);
    }

    @Override
    public QuestionPost save(QuestionPost questionPost) {
        return questionPostRepository.save(questionPost);
    }

    @Override
    public void update(QuestionPost questionPost) {
        questionPostRepository.update(questionPost);
    }

    @Override
    public void delete(Long id) {
        questionPostRepository.delete(id);
    }
}
