package com.kakao.cafe.domain.answer;

public interface AnswerRepository {
    Answer save(Answer answer);
    Answer findById(int id);
    void deleteById(int id);
}
