package com.kakao.cafe.article.web;

import com.kakao.cafe.article.service.ArticleService;
import com.kakao.cafe.article.web.dto.ArticleModifyDto;
import com.kakao.cafe.article.web.dto.ArticleSaveDto;
import com.kakao.cafe.article.web.dto.ArticleShowDto;
import com.kakao.cafe.reply.web.dto.ReplyShowDto;
import com.kakao.cafe.user.domain.User;
import java.util.List;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/articles")
@Controller
public class ArticleController {

    private final ArticleService articleService;
    private static final String SESSION_NAME = "sessionedUser";

    @PostMapping
    public String articleAdd(@Valid ArticleSaveDto articleSaveDto, HttpSession httpSession) {
        User user = (User) httpSession.getAttribute(SESSION_NAME);
        articleSaveDto.setWriter(user.getUserId());
        articleService.addArticle(articleSaveDto);
        return "redirect:/";
    }

    @GetMapping
    public String articleList(Model model) {
        List<ArticleShowDto> articleShowDtoList = articleService.findAllArticle();
        model.addAttribute("articles", articleShowDtoList);
        return "qna/list";
    }

    @GetMapping("/{index}")
    public String articleDetail(@PathVariable("index") Long index, Model model) {
        ArticleShowDto articleShowDto = articleService.findArticle(index);
        log.info(articleShowDto.getReplies().size() + "개의 댓글 있음");
        for (ReplyShowDto r : articleShowDto.getReplies()) {
            log.info(r.getContents());
        }
        model.addAttribute("article", articleShowDto);
        return "qna/show";
    }

    @GetMapping("/{index}/editform")
    public String articleEditForm(@PathVariable("index") Long index, Model model,
        HttpSession httpSession) {
        // 수정 form 요청
        // 자신의 글이 맞는지 validate
        if (!validate(httpSession, index)) {
            return "redirect:/unauthorized";
        }
        ArticleShowDto articleShowDto = articleService.findArticle(index);
        model.addAttribute("article", articleShowDto);
        return "qna/editform";
    }

    @PutMapping("/{index}")
    public String questionModify(@Valid ArticleModifyDto articleModifyDto,
        @PathVariable("index") Long index) {
        articleService.modifyArticle(index, articleModifyDto);
        return "redirect:/articles/" + index;
    }

    @DeleteMapping("/{index}")
    public String questionRemove(@PathVariable("index") Long index, HttpSession httpSession) {
        if (!validate(httpSession, index)) {
            return "redirect:/unauthorized";
        }
        articleService.removeArticle(index);
        return "redirect:/";
    }

    private boolean validate(HttpSession httpSession, Long index) {
        return ((User) httpSession.getAttribute(SESSION_NAME)).getUserId()
            .equals(articleService.findArticleWriter(index));
    }
}
