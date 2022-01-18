package com.kakao.cafe.service;

import com.kakao.cafe.domain.Article;
import com.kakao.cafe.domain.User;
import com.kakao.cafe.exception.NotSessionInfo;
import com.kakao.cafe.repository.ArticleRepository;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;

@Service
public class ArticleService {
    private final ArticleRepository articleRepository;

    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public Long save(Article article) {
        return articleRepository.save(article).orElseThrow(() -> new NullPointerException("게시글이 저장되지 않았습니다."));
    }


    public List<Article> getAllQuestions() {
        return articleRepository.getAllQuestions();
    }

    public Article findById(String id, HttpSession httpSession) {
        if(httpSession.getAttribute("curUser") == null) {
            throw new NotSessionInfo("글을 읽으려면 로그인을 하십시오");
        }
        return articleRepository.findById(id);
    }

    public void deleteByWriter(String writer) {
        articleRepository.deleteByWriter(writer);
    }

    public void isWriter(Article article, HttpSession httpSession) {
        User curUser = (User)httpSession.getAttribute("curUser");
        String curUserId = curUser.getUserId();
        if(!article.isWriter(curUserId)) {
            throw new IllegalArgumentException("다른 사람의 글을 수정, 삭제할 수 없습니다.");
        }
    }

    public void update(Article article) {
        articleRepository.update(article);
    }

    public void deleteById(String id) {
        articleRepository.deleteById(id);
    }
}
