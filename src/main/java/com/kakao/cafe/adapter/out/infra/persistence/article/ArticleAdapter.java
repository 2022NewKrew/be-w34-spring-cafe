package com.kakao.cafe.adapter.out.infra.persistence.article;

import com.kakao.cafe.application.article.dto.ArticleInfo;
import com.kakao.cafe.application.article.dto.ArticleList;
import com.kakao.cafe.application.article.dto.UpdateRequest;
import com.kakao.cafe.application.article.dto.WriteRequest;
import com.kakao.cafe.application.article.port.out.DeleteArticlePort;
import com.kakao.cafe.application.article.port.out.GetArticleInfoPort;
import com.kakao.cafe.application.article.port.out.RegisterArticlePort;
import com.kakao.cafe.application.article.port.out.UpdateArticlePort;
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

    public ArticleAdapter(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @Override
    public void registerArticle(WriteRequest writeRequest)
        throws IllegalWriterException, IllegalTitleException, IllegalDateException, IllegalUserIdException {
        articleRepository.save(
            new Article.Builder()
                .userId(writeRequest.getUserId())
                .writer(writeRequest.getWriter())
                .title(writeRequest.getTitle())
                .contents(writeRequest.getContents())
                .createdAt(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")))
                .deleted(false)
                .build()
        );
    }

    @Override
    public void updateArticle(UpdateRequest updateRequest)
        throws IllegalWriterException, IllegalTitleException, IllegalDateException, IllegalUserIdException {
        Article article = new Article.Builder()
            .userId(updateRequest.getUserId())
            .writer(updateRequest.getWriter())
            .title(updateRequest.getTitle())
            .contents(updateRequest.getContents())
            .createdAt(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")))
            .deleted(false)
            .build();
        article.setId(updateRequest.getId());
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
}
