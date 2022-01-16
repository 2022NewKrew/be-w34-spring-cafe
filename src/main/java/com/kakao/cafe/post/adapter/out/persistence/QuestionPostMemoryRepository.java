package com.kakao.cafe.post.adapter.out.persistence;

import com.kakao.cafe.post.domain.QuestionPost;
import com.kakao.cafe.post.domain.QuestionPostRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Repository
@Qualifier("simple-question-db")
public class QuestionPostMemoryRepository implements QuestionPostRepository {

    private final Map<Long, QuestionPost> postMap;

    public QuestionPostMemoryRepository() {
        this.postMap = new ConcurrentHashMap<>();
    }

    @Override
    public QuestionPost save(QuestionPost questionPost) {
        postMap.put(questionPost.getQuestionPostId(), questionPost);

        return questionPost;
    }

    @Override
    public Optional<QuestionPost> findById(Long id) {
        QuestionPost questionPost = postMap.get(id);
        return Optional.of(questionPost);
    }

    @Override
    public List<QuestionPost> findAll() {
        return new ArrayList<>(postMap.values());
    }

    @Override
    public void update(QuestionPost questionPost) {
        postMap.put(questionPost.getQuestionPostId(), questionPost);
    }

    @Override
    public void delete(Long id) {
        postMap.remove(id);
    }

    @Override
    public void deleteAll() {
        postMap.clear();
    }
}
