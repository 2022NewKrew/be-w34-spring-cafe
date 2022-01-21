package com.kakao.cafe.service;

import com.kakao.cafe.controller.dto.request.ArticleRegisterRequestDto;
import com.kakao.cafe.domain.Article;
import com.kakao.cafe.domain.Reply;
import com.kakao.cafe.domain.User;
import com.kakao.cafe.exception.ArticleNotFoundException;
import com.kakao.cafe.repository.article.ArticleRepository;
import com.kakao.cafe.repository.reply.ReplyRepository;
import com.kakao.cafe.service.dto.ArticleUpdateDto;
import com.kakao.cafe.service.dto.ReplyRegisterDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ArticleService {

    private final UserService userService;
    private final ArticleRepository articleRepository;
    private final ReplyRepository replyRepository;


    public void register(ArticleRegisterRequestDto articleRegisterRequestDto) {
        User writer = userService.findUserByUserId(articleRegisterRequestDto.getWriterId());
        Article article = Article.builder()
                .title(articleRegisterRequestDto.getTitle())
                .writer(writer)
                .contents(articleRegisterRequestDto.getContents())
                .build();
        articleRepository.save(article);
    }

    public void registerReply(ReplyRegisterDto replyRegisterDto) {
        Reply reply = Reply.builder()
                .articleId(findById(replyRegisterDto.getArticleId()).getId())
                .writer(userService.findUserByUserId(replyRegisterDto.getWriterId()))
                .comment(replyRegisterDto.getComment())
                .build();

        replyRepository.save(reply);
    }

    public List<Reply> findReplysByArticleId(Long articleId) {
        return replyRepository.findByArticleId(articleId);
    }

    public Reply findReplyById(Long replyId) {
        return replyRepository.findById(replyId).orElseThrow(() -> new IllegalArgumentException("댓글을 찾을수 없습니다."));
    }


    public Article findById(Long id) {
        return articleRepository.findById(id)
                .map(this::addComments)
                .orElseThrow(() -> new ArticleNotFoundException("게시글을 찾을수 없습니다."));
    }

    public List<Article> findAll() {
        return articleRepository.findAll();
    }


    public void update(ArticleUpdateDto articleUpdateDto) {
        Article foundArticle = findById(articleUpdateDto.getId());
        foundArticle.update(articleUpdateDto.getTitle(), articleUpdateDto.getContents());
        articleRepository.update(foundArticle);
    }


    public void deleteById(Long id) {
        articleRepository.deleteById(id);
    }

    private Article addComments(Article article) {
        List<Reply> replys = findReplysByArticleId(article.getId());
        for (Reply reply : replys) {
            article.addReply(reply);
        }
        return article;
    }

    public void deleteReplyById(Long replyId) {
        replyRepository.deleteById(replyId);
    }
}
