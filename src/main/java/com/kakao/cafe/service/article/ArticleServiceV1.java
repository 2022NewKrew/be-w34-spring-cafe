package com.kakao.cafe.service.article;

import com.kakao.cafe.domain.article.Article;
import com.kakao.cafe.domain.article.Comments;
import com.kakao.cafe.domain.member.Member;
import com.kakao.cafe.domain.member.UserId;
import com.kakao.cafe.repository.article.ArticleRepository;
import com.kakao.cafe.repository.comment.CommentRepository;
import com.kakao.cafe.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ArticleServiceV1 implements ArticleService {

    private final ArticleRepository articleRepository;
    private final MemberRepository memberRepository;
    private final CommentRepository commentRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Article> inquireAllArticles() {
        return articleRepository.findAllArticle();
    }

    @Override
    public void postArticle(Article article, String userId) {
        Member member = memberRepository.findByUserId(new UserId(userId));
        article.setAuthor(member);
        articleRepository.save(article);
    }

    @Override
    @Transactional(readOnly = true)
    public Article inquireOneArticle(Long articleId) {
        Article article = articleRepository.findArticle(articleId);
        article.setComments(new Comments(commentRepository.findAllOfArticle(article)));
        return article;
    }

    @Override
    public void editArticle(Long articleId, Article article) {

    }

    @Override
    public void deleteArticle(Long articleId) {

    }

    @Override
    public void deleteAllArticles() {

    }
}
