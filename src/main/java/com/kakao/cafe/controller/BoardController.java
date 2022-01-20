package com.kakao.cafe.controller;

import com.kakao.cafe.application.dto.ArticleDto;
import com.kakao.cafe.application.dto.CommentDto;
import com.kakao.cafe.application.service.BoardService;
import com.kakao.cafe.util.annotation.BoardCheck;
import com.kakao.cafe.util.exception.NotMyArticleException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/article/modify/{articleId}")
    @BoardCheck
    public String goModifyArticleView(@PathVariable long articleId, Model model) {
        model.addAttribute("article", boardService.findArticleByArticleId(articleId));
        return "board/modify";
    }

    @PutMapping("/article/modify/{articleId}")
    @BoardCheck
    public String modifyArticle(@PathVariable long articleId, String title, String content) {
        boardService.modifyArticle(ArticleDto.builder()
                .articleId(articleId)
                .title(title)
                .content(content).build());
        return "redirect:/board/view/" + articleId;
    }

    @DeleteMapping("/article/delete/{articleId}")
    @BoardCheck
    public String deleteArticle(@PathVariable long articleId, String writerId) {
        List<CommentDto> allComments = boardService.findCommentsByArticleId(articleId);
        List<CommentDto> writerComments = boardService.findCommentByWriterId(writerId);

        if (allComments.size() != writerComments.size()) {
            throw new NotMyArticleException("해당 게시글에 게시자가 아닌 다른 계정의 댓글이 존재하여 삭제할 수 없습니다.");
        }

        boardService.deleteCommentByArticleId(articleId);
        boardService.deleteArticle(articleId);
        return "redirect:/board/list";
    }

    @PostMapping("/comment/write")
    @ResponseBody
    public ResponseEntity<CommentDto> writeComment(@RequestBody CommentDto commentDto) {
        return ResponseEntity.ok(boardService.writeComment(commentDto.getArticleId(), commentDto));
    }

    @DeleteMapping("/comment/delete/{articleId}/{commentId}")
    @BoardCheck(type = BoardCheck.Type.COMMENT)
    @ResponseBody
    public ResponseEntity<Object> deleteComment(@PathVariable("articleId") long articleId,
                                @PathVariable("commentId") long commentId) {
        boardService.deleteComment(articleId, commentId);

        return ResponseEntity.ok().build();
    }
}
