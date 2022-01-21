package com.kakao.cafe.adapter.out.infra.persistence.article;

import com.kakao.cafe.adapter.out.infra.persistence.reply.ReplyRepository;
import com.kakao.cafe.application.article.dto.ArticleDetail;
import com.kakao.cafe.application.article.dto.ArticleInfo;
import com.kakao.cafe.application.article.dto.ArticleList;
import com.kakao.cafe.application.article.dto.UpdateArticleRequest;
import com.kakao.cafe.application.article.dto.WriteArticleRequest;
import com.kakao.cafe.application.article.port.out.DeleteArticlePort;
import com.kakao.cafe.application.article.port.out.GetArticleInfoPort;
import com.kakao.cafe.application.article.port.out.RegisterArticlePort;
import com.kakao.cafe.application.article.port.out.UpdateArticlePort;
import com.kakao.cafe.application.reply.dto.Replies;
import com.kakao.cafe.domain.article.Article;
import com.kakao.cafe.domain.article.exceptions.ArticleNotExistException;
import com.kakao.cafe.domain.article.exceptions.IllegalDateException;
import com.kakao.cafe.domain.article.exceptions.IllegalTitleException;
import com.kakao.cafe.domain.article.exceptions.IllegalWriterException;
import com.kakao.cafe.domain.user.exceptions.IllegalUserIdException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Repository;

@Repository
public class ArticleAdapter implements RegisterArticlePort, GetArticleInfoPort, UpdateArticlePort, DeleteArticlePort {

    private final ArticleRepository articleRepository;
    private final ReplyRepository replyRepository;

    public ArticleAdapter(ArticleRepository articleRepository, ReplyRepository replyRepository) {
        this.articleRepository = articleRepository;
        this.replyRepository = replyRepository;
    }

    @Override
    public void registerArticle(WriteArticleRequest writeArticleRequest)
        throws IllegalWriterException, IllegalTitleException, IllegalDateException, IllegalUserIdException {
        articleRepository.save(
            new Article.Builder()
                .userId(writeArticleRequest.getUserId())
                .writer(writeArticleRequest.getWriter())
                .title(writeArticleRequest.getTitle())
                .contents(writeArticleRequest.getContents())
                .createdAt(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")))
                .deleted(false)
                .build()
        );
    }

    @Override
    public void updateArticle(UpdateArticleRequest updateArticleRequest)
        throws IllegalWriterException, IllegalTitleException, IllegalDateException, IllegalUserIdException {
        Article article = new Article.Builder()
            .userId(updateArticleRequest.getUserId())
            .writer(updateArticleRequest.getWriter())
            .title(updateArticleRequest.getTitle())
            .contents(updateArticleRequest.getContents())
            .createdAt(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")))
            .deleted(false)
            .build();
        article.setId(updateArticleRequest.getId());
        articleRepository.update(article);
    }

    @Override
    public void delete(int id) {
        articleRepository.deleteById(id);
    }

    @Override
    public ArticleList getListOfAllArticles() {
        List<ArticleInfo> articleList = articleRepository.getAllArticleList()
                                                         .stream()
                                                         .map(ArticleInfo::from)
                                                         .collect(Collectors.toList());

        return ArticleList.from(articleList);
    }

    @Override
    public Article findArticleById(int id) throws ArticleNotExistException {
        return articleRepository.findById(id).orElseThrow(
            () -> new ArticleNotExistException("존재하지 않는 게시글입니다.")
        );
    }

    @Override
    public ArticleDetail findArticleDetailById(int id) throws ArticleNotExistException {
        Article article = findArticleById(id);
        Replies replies = Replies.from(replyRepository.getAllReplyListByArticleId(id));

        return new ArticleDetail.Builder().id(id)
                                          .userId(article.getUserId())
                                          .writer(article.getWriter())
                                          .title(article.getTitle())
                                          .contents(article.getContents())
                                          .createdAt(article.getCreatedAt())
                                          .replyList(replies)
                                          .countOfReplies(replies.getCountOfReplies())
                                          .build();
    }
}
