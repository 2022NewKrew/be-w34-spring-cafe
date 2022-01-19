package com.kakao.cafe.article.service;

import com.kakao.cafe.article.dto.request.ArticleCreateRequest;
import com.kakao.cafe.article.dto.request.ArticleUpdateRequest;
import com.kakao.cafe.article.dto.response.ArticleDetailResponse;
import com.kakao.cafe.article.entity.Article;
import com.kakao.cafe.article.exception.ArticleNotFoundException;
import com.kakao.cafe.article.mapper.ArticleMapper;
import com.kakao.cafe.article.repository.ArticleRepository;
import com.kakao.cafe.user.dto.response.UserInfoResponse;
import com.kakao.cafe.user.entity.User;
import com.kakao.cafe.user.exception.ForbiddenException;
import com.kakao.cafe.user.exception.UserNotFoundException;
import com.kakao.cafe.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;
    private final UserRepository userRepository;
    private final ArticleMapper articleMapper;

    /**
     * 게시글 작성 로직
     * @param req: 컨트롤러에 들어온 게시글 작성 정보
     */
    public void createArticle(ArticleCreateRequest req, Long writerId) {

        Article article = articleMapper.articleCreateRequestToEntity(req);
        Optional<User> writer = userRepository.findById(writerId);
        article.setWriter(writer.orElseThrow(UserNotFoundException::new));

        this.articleRepository.save(article);
    }

    /**
     * 게시글 리스트 반환 로직
     * @return List<ArticleDetailResponse>: 게시글 정보 DTO 로 이루어진 리스트
     */
    public List<ArticleDetailResponse> getArticleList() {
        List<Article> articleList = this.articleRepository.findAll();

        return articleList.stream()
                          .map(articleMapper::articleToArticleDetailResponse)
                          .collect(Collectors.toUnmodifiableList());
    }

    /**
     * 입력 인자로 들어온 id에 해당하는 게시글 정보 반환
     * @param id: 원하는 게시글의 id
     * @throws ArticleNotFoundException : 해당 ID 의 게시글의 없을 경우 발생
     * @return ArticleDetailResponse: 게시글 정보 DTO
     */
    public ArticleDetailResponse getArticleDetail(Long id) {
        Optional<Article> article = this.articleRepository.findById(id);

        return articleMapper.articleToArticleDetailResponse(
                article.orElseThrow(ArticleNotFoundException::new)
        );
    }

    /**
     * 입력 인자로 들어온 id에 해당하는 Article 의 정보를 수정하는 로직
     * @param id: 수정할 게시글의 ID(PK)
     * @param req: 게시글 수정 정보
     */
    public void updateArticle(Long id, ArticleUpdateRequest req) {
        Article article = this.articleRepository.findById(id)
                .orElseThrow(ArticleNotFoundException::new);

        this.changeArticleInfo(article, req);

        this.articleRepository.update(article);
    }

    /**
     * 입력 인자로 들어온 id에 해당하는 Article 을 삭제하는 로직
     * @param id: 수정할 게시글의 ID(PK)
     */
    public void deleteArticle(Long id) {
        Article article = this.articleRepository.findById(id)
                                                .orElseThrow(ArticleNotFoundException::new);
        this.articleRepository.delete(article);
    }

    /**
     * 현재 로그인 중인 유저가 articleId를 id로 갖는 게시글을 다룰 수 있는지 판단하는 메서드
     * @throws ForbiddenException: 자신의 게시글이 아니라면 발생
     */
    public void validateUser(Long userPK, Long articleId) {
        Article article = this.articleRepository.findById(articleId)
                                                .orElseThrow(ArticleNotFoundException::new);
        if(!Objects.equals(article.getWriter().getId(), userPK)) {
            throw new ForbiddenException();
        }
    }

    public boolean isModifiable(UserInfoResponse user, ArticleDetailResponse articleDetail) {
        if(user == null) {
            return false;
        }

        return Objects.equals(user.getId(), articleDetail.getWriter().getId());
    }

    private void changeArticleInfo(Article article, ArticleUpdateRequest req) {
        if(!req.getTitle().isBlank()) {
            article.setTitle(req.getTitle());
        }
        if(!req.getContents().isBlank()) {
            article.setContents(req.getContents());
        }
    }
}
