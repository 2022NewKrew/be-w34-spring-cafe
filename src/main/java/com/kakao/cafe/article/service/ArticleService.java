package com.kakao.cafe.article.service;

import com.kakao.cafe.article.domain.Article;
import com.kakao.cafe.article.domain.Reply;
import com.kakao.cafe.article.dto.ArticleCreateDTO;
import com.kakao.cafe.article.dto.ArticleUpdateDTO;
import com.kakao.cafe.article.dto.ReplyCreateDTO;
import com.kakao.cafe.article.dto.ReplyViewDTO;
import com.kakao.cafe.article.repository.ArticleJdbcRepository;
import com.kakao.cafe.article.repository.ArticleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ArticleService {
    private ArticleRepository articleRepository;

    public ArticleService(ArticleRepository articleRepository){
        this.articleRepository = articleRepository;
    }

    public void articleCreate(ArticleCreateDTO articleCreateDTO){
        Article article = new Article(articleCreateDTO);
        articleRepository.addArticle(article);
    }

    public void articleUpdate(ArticleUpdateDTO articleUpdateDTO){
        articleRepository.updateArticle(articleUpdateDTO.getSequence(), articleUpdateDTO.getTitle(), articleUpdateDTO.getContents());
    }

    public void articleDelete(Long sequence){
        Article article = getArticleBySequence(sequence);
        articleRepository.deleteArticle(article);
    }

    public Article getArticleBySequence(Long sequence){
        Article article = articleRepository.getArticleByCondition("sequence", sequence.toString());

        if(article == null){
            throw new RuntimeException("해당 글이 존재하지 않습니다.");
        }
        return article;
    }

    public List<Article> getAllArticles(){
        return articleRepository.getArticlesNotDeleted();
    }


    public void replyCreate(ReplyCreateDTO replyCreateDTO){
        articleRepository.addReply(replyCreateDTO.getUserId(), replyCreateDTO.getArticleSeq(), replyCreateDTO.getContents());
    }

    public List<ReplyViewDTO> getReplies(Long articleSeq){
        List<ReplyViewDTO> replies = articleRepository.getRepliesByArticleSeqWithUser(articleSeq);
        return replies;
    }
}
