package com.kakao.cafe.question;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * HashMap 으로 만든 QuestionRepository 의 구현체입니다.
 * 생성시 2개의 질문글을 등록합니다.
 *
 * @author jm.hong
 * @deprecated H2 데이터베이스가 나와 해당 구현체는 사라집니다.
 */
@Slf4j
public class QuestionRepositoryHash implements QuestionRepository {

    private static Map<Long, Question> inMemoryDatabase = new HashMap<>();
    private static long autoIncrementNumber = 0L;

    // 임시 데이터입니다.
    static {
        Question question1 = new Question();
        question1.setId(autoIncrementNumber);
        question1.setTitle("KAKAO 새로운 크루!");
        question1.setContents("새로운 크루가 오시는걸 아시나요?");
        question1.setWriter("jm.hong");
        question1.updateTime();
        inMemoryDatabase.put(autoIncrementNumber++, question1);

        Question question2 = new Question();
        question2.setId(autoIncrementNumber);
        question2.setTitle("놀라운 소식이 있습니다!!");
        question2.setContents("정말 놀랍지 않나요? 다음에 다시 알아보도록 하겠습니다!");
        question2.setWriter("jm.hong");
        question2.updateTime();
        inMemoryDatabase.put(autoIncrementNumber++, question2);

    }

    @Override
    public Long save(Question question) {
        question.setId(autoIncrementNumber);
        inMemoryDatabase.put(autoIncrementNumber, question);
        return autoIncrementNumber++;
    }

    @Override
    public Question findOne(Long id) {
        return inMemoryDatabase.get(id);
    }

    @Override
    public List<Question> findAll() {
        // TODO stream 으로 변경
        List<Question> questions = new ArrayList<>();
        List<Long> keys = new ArrayList<>(inMemoryDatabase.keySet());

        for (Long key : keys) {
            questions.add(inMemoryDatabase.get(key));
        }

        return questions;
    }

    @Override
    public boolean deleteOne(Long id) {
        return false;
    }

    @Override
    public boolean deleteOne(Long id, Long memberId) {
        return false;
    }
}
