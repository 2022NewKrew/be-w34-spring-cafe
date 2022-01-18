package com.kakao.cafe.adapter.out.infra.persistence.article;

import com.kakao.cafe.application.article.dto.ArticleInfo;
import com.kakao.cafe.application.article.dto.ArticleList;
import com.kakao.cafe.application.article.dto.WriteRequest;
import com.kakao.cafe.application.article.port.out.GetArticleInfoPort;
import com.kakao.cafe.application.article.port.out.RegisterArticlePort;
import com.kakao.cafe.domain.article.Article;
import com.kakao.cafe.domain.article.exceptions.ArticleNotExistException;
import com.kakao.cafe.domain.article.exceptions.IllegalDateException;
import com.kakao.cafe.domain.article.exceptions.IllegalTitleException;
import com.kakao.cafe.domain.article.exceptions.IllegalWriterException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Repository;

@Repository
public class ArticleAdapter implements RegisterArticlePort, GetArticleInfoPort {

    private final ArticleRepository articleRepository;

    public ArticleAdapter(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @Override
    public void registerArticle(WriteRequest writeRequest)
        throws IllegalWriterException, IllegalTitleException, IllegalDateException {
        articleRepository.save(
            new Article.Builder()
                .writer(writeRequest.getWriter())
                .title(writeRequest.getTitle())
                .contents(writeRequest.getContents())
                .createdAt(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")))
                .build()
        );
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
