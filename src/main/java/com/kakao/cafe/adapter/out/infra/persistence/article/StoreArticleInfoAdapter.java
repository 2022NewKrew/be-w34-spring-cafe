package com.kakao.cafe.adapter.out.infra.persistence.article;

import com.kakao.cafe.application.article.dto.ArticleInfo;
import com.kakao.cafe.application.article.dto.ArticleList;
import com.kakao.cafe.application.article.dto.WriteRequest;
import com.kakao.cafe.application.article.port.out.GetArticleInfoPort;
import com.kakao.cafe.application.article.port.out.RegisterArticlePort;
import com.kakao.cafe.domain.article.Article;
import com.kakao.cafe.domain.article.exceptions.ArticleNotExistException;
import com.kakao.cafe.domain.article.exceptions.IllegalDateException;
import com.kakao.cafe.domain.article.exceptions.IllegalIdException;
import com.kakao.cafe.domain.article.exceptions.IllegalTitleException;
import com.kakao.cafe.domain.article.exceptions.IllegalWriterException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import org.springframework.stereotype.Repository;

@Repository
public class StoreArticleInfoAdapter implements RegisterArticlePort, GetArticleInfoPort {

    private static final int FIRST_ID = 1;

    private final ArticleInfoRepository inMemoryArticleInfoRepository;
    private final AtomicInteger atomicInt = new AtomicInteger(FIRST_ID);

    public StoreArticleInfoAdapter(ArticleInfoRepository inMemoryArticleInfoRepository) {
        this.inMemoryArticleInfoRepository = inMemoryArticleInfoRepository;
    }

    @Override
    public void registerArticle(WriteRequest writeRequest)
        throws IllegalIdException, IllegalWriterException, IllegalTitleException, IllegalDateException {
        inMemoryArticleInfoRepository.save(new Article.Builder()
                                               .id(atomicInt.getAndIncrement())
                                               .writer(writeRequest.getWriter())
                                               .title(writeRequest.getTitle())
                                               .contents(writeRequest.getContents())
                                               .createdAt(
                                                   LocalDateTime.now()
                                                                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))
                                               )
                                               .build());
    }

    @Override
    public ArticleList getListOfAllArticles() {
        List<ArticleInfo> articleList = inMemoryArticleInfoRepository.getAllArticleList()
                                                                     .stream()
                                                                     .map(a -> new ArticleInfo(
                                                                         a.getId(),
                                                                         a.getWriter(),
                                                                         a.getTitle(),
                                                                         a.getCreatedAt()
                                                                     ))
                                                                     .collect(Collectors.toList());

        return ArticleList.from(articleList);
    }

    public Article findArticleByIndex(int index) throws ArticleNotExistException {
        Article article = inMemoryArticleInfoRepository.findByIndex(index).orElse(null);

        if (article == null) {
            throw new ArticleNotExistException("존재하지 않는 게시글입니다.");
        }

        return article;
    }
}
