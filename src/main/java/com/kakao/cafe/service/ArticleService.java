package com.kakao.cafe.service;

import org.springframework.stereotype.Service;

import com.kakao.cafe.domain.Article;
import com.kakao.cafe.domain.Reply;
import com.kakao.cafe.dto.ArticleDto;
import com.kakao.cafe.dto.ArticlesDto;
import com.kakao.cafe.dto.ReplyDto;
import com.kakao.cafe.repository.ArticleRepository;

@Service
public class ArticleService {
    private final ArticleRepository articleRepository;

    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public void save(ArticleDto articleDTO) {
        articleRepository.save(articleDTO.toArticle());
    }

    public void modify(Integer index, String title, String content) {
        ArticleDto articleDTO = articleRepository.findByIndex(index);
        Article modifiedArticle = new Article(title, content, articleDTO.getWriter(), index);
        articleRepository.update(modifiedArticle);
    }

    public void delete(Integer articleIndex) {
        articleRepository.delete(articleIndex);
    }

    public ArticlesDto findAll() {
        return new ArticlesDto(articleRepository.findAll());
    }

    public ArticleDto findById(Integer articleIndex) {
        return articleRepository.findByIndex(articleIndex);
    }

    public Reply findReplyById(Integer replyIndex) {
        return articleRepository.findReplyByIndex(replyIndex);
    }

    public void reply(ReplyDto replyDto) {
        articleRepository.saveReply(new Reply(
                replyDto.getArticleIndex(),
                replyDto.getWriter(),
                replyDto.getContent()
        ));
    }

    public void deleteReply(Integer replyIndex) {
        articleRepository.deleteReply(replyIndex);
    }
}
