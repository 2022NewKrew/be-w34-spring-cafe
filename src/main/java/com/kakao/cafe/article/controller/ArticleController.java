package com.kakao.cafe.article.controller;

import com.kakao.cafe.article.dto.request.ArticleCreateRequest;
import com.kakao.cafe.article.dto.request.ArticleUpdateRequest;
import com.kakao.cafe.article.dto.response.ArticleDetailResponse;
import com.kakao.cafe.article.exception.ArticleNotFoundException;
import com.kakao.cafe.article.service.ArticleService;
import com.kakao.cafe.user.dto.response.UserInfoResponse;
import com.kakao.cafe.user.mapper.exception.ForbiddenException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

@Controller
@Slf4j
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleService articleService;

    /**
     * 메인 페이지 접속 [GET]
     */
    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public String getIndexPage(Model model){
        log.info("[GET] / - 메인 페이지 접속(글 리스트)");
        List<ArticleDetailResponse> articleList = this.articleService.getArticleList();
        model.addAttribute("articles", articleList);

        return "../static/index";
    }

    /**
     * 게시글 작성 페이지 접속 [GET]
     */
    @GetMapping("/articles/write")
    @ResponseStatus(HttpStatus.OK)
    public String getArticleCreatePage() {
        log.info("[GET] /articles/write - 게시글 작성 페이지 접속");
        return "article/write";
    }

    /**
     * 게시글 상세 페이지 접속 [GET]
     * @param id: 보고자 하는 게시글의 ID(PK)
     * @throws ArticleNotFoundException: 해당 ID 의 Article 이 존재하지 않을 경우 발생
     */

    @GetMapping("/articles/{id}")
    @ResponseStatus(HttpStatus.OK)
    public String getArticleDetailPage(Model model, @PathVariable("id") Long id, HttpSession session) {
        log.info("[GET] /articles/{} - (id: {}) 게시글 상세 페이지 접속", id, id);

        ArticleDetailResponse articleDetail = this.articleService.getArticleDetail(id);
        model.addAttribute("article", articleDetail);

        boolean possibleToWrite = isPossibleToControl(session, articleDetail);

        model.addAttribute("possibleToControl", possibleToWrite);
        return "article/show";
    }

    /**
     * 게시글 작성 요청 [POST]
     * @param req: 게시글 작성 정보
     */
    @PostMapping("/articles")
    public String createArticle(@Valid ArticleCreateRequest req, HttpSession session) {
        log.info("[POST] /articles - 게시글 작성 요청");

        UserInfoResponse user = this.getUserInfoInSession(session);
        Long writerId = user.getId();
        this.articleService.createArticle(req, writerId);
        return "redirect:/";
    }

    /**
     * 게시글 수정 페이지 접속 [GET]
     */
    @GetMapping("/articles/{id}/update")
    @ResponseStatus(HttpStatus.OK)
    public String getArticleUpdatePage(Model model, HttpSession session, @PathVariable("id") Long id) {
        log.info("[GET] /articles/{}/update - (id: {}) 게시글 수정 페이지 접속", id, id);

        ArticleDetailResponse article = this.articleService.getArticleDetail(id);

        if(!this.isPossibleToControl(session, article)) {
            throw new ForbiddenException();
        }

        model.addAttribute("article", article);
        return "/article/update";
    }

    /**
     * 게시글 수정 요청 [PUT]
     * @param req - 게시글 수정 정보
     * @param id - 수정할 게사글 id(PK)
     */
    @PutMapping("/articles/{id}")
    public String updateArticle(ArticleUpdateRequest req, HttpSession session, @PathVariable("id") Long id) {
        log.info("[PUT] /articles/{} - (id: {}) 게시글 수정 요청", id, id);

        UserInfoResponse user = this.getUserInfoInSession(session);

        validateUser(user, id);

        this.articleService.updateArticle(id, req);

        return "redirect:/articles/" + id;
    }

    /**
     * 게시글 삭제 요청 [DELETE]
     * @param id - 삭제할 게시글 id(PK)
     */
    @DeleteMapping("articles/{id}")
    public String deleteArticle(HttpSession session, @PathVariable("id") Long id) {
        log.info("[DELETE] /articles/{} - (id: {}) 게시글 삭제 요청", id, id);

        UserInfoResponse user = this.getUserInfoInSession(session);

        validateUser(user, id);

        this.articleService.deleteArticle(id);

        return "redirect:/";
    }


    private UserInfoResponse getUserInfoInSession(HttpSession session) {
        return (UserInfoResponse) session.getAttribute("user");
    }

    private boolean isPossibleToControl(HttpSession session, ArticleDetailResponse articleDetail) {
        UserInfoResponse userInfo = this.getUserInfoInSession(session);
        if(userInfo == null) {
            return false;
        }

        return Objects.equals(userInfo.getId(), articleDetail.getWriter().getId());
    }

    private void validateUser(UserInfoResponse user, Long articleId) {
        ArticleDetailResponse article = articleService.getArticleDetail(articleId);
        if(!Objects.equals(user.getId(), article.getWriter().getId())) {
            throw new ForbiddenException();
        }
    }
}
