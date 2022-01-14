package com.kakao.cafe.controller;

import com.kakao.cafe.model.dto.ArticleDto;
import com.kakao.cafe.model.dto.CommentDto;
import com.kakao.cafe.model.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("/board")
public class BoardController {
    private final BoardService boardService;

    @GetMapping("/list")
    public String goAllView(Model model) {
        List<ArticleDto> articleList = boardService.findAllArticle();
        articleList.sort((a1, a2) -> Long.compare(a2.getArticleId(), a1.getArticleId()));
        model.addAttribute("articles", articleList);
        return "board/list";
    }

    @GetMapping("/write")
    public String goWriteView() {
        return "board/write";
    }

    @PostMapping("/write")
    public String writeArticle(String title, String writerId, String content) {
        ArticleDto articleDto = ArticleDto.builder()
                .title(title)
                .writerId(writerId)
                .content(content).build();

        boardService.writeArticle(articleDto);

        return "redirect:/board/list";
    }

    @GetMapping("/view/{articleId}")
    public String viewArticle(@PathVariable long articleId, Model model) {
        model.addAttribute("article", boardService.findArticleByArticleId(articleId));
        model.addAttribute("comments", boardService.findCommentsByArticleId(articleId));
        return "board/view";
    }

    @GetMapping("/modify/{articleId}")
    public String goModifyArticleView(@PathVariable long articleId, Model model) {
        model.addAttribute("article", boardService.findArticleByArticleId(articleId));
        return "board/modify";
    }

    @PutMapping("/modify/{articleId}")
    public String modifyArticle(@PathVariable long articleId, String title, String content) {
        boardService.modifyArticle(ArticleDto.builder()
                .articleId(articleId)
                .title(title)
                .content(content).build());
        return "redirect:/board/view/" + articleId;
    }

    @DeleteMapping("/delete/{articleId}")
    public String deleteArticle(@PathVariable long articleId) {
        boardService.deleteArticle(articleId);
        return "redirect:/board/list";
    }

    @PostMapping("/write/comment")
    public String writeComment(String writerId, String content, long articleId) {
        CommentDto commentDto = CommentDto.builder()
                .writerId(writerId)
                .content(content).build();

        boardService.writeComment(articleId, commentDto);

        return "redirect:/board/view/" + articleId;
    }

    @DeleteMapping("/delete/{articleId}/{commentId}")
    public String deleteComment(@PathVariable("articleId") long articleId,
                                @PathVariable("commentId") long commentId) {
        boardService.deleteComment(articleId, commentId);

        return "redirect:/board/view/" + articleId;
    }
}
