package com.kakao.cafe.controller;

import com.kakao.cafe.application.dto.ArticleDto;
import com.kakao.cafe.application.dto.CommentDto;
import com.kakao.cafe.application.dto.PaginationDto;
import com.kakao.cafe.application.service.BoardService;
import com.kakao.cafe.util.annotation.BoardCheck;
import com.kakao.cafe.util.exception.NotMyArticleException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

import static com.kakao.cafe.util.Constants.DEFAULT_COUNT_PER_PAGE;

@RequiredArgsConstructor
@Controller
@RequestMapping("/board")
public class BoardController {
    private final BoardService boardService;

    @GetMapping("/articles")
    public String goAllArticlesView(@RequestParam(required = false) Map<String, String> params, Model model) {
        long currentPage = params.get("page") == null ? 1 : Long.parseLong(params.get("page"));
        int countPerPage = params.get("cpp") == null ? DEFAULT_COUNT_PER_PAGE : Integer.parseInt(params.get("cpp"));

        List<ArticleDto> articleList = boardService.findArticlesByCurrentPageAndCountPerPage(currentPage, countPerPage);
        model.addAttribute("articles", articleList);
        return "board/list";
    }

    @GetMapping("/articles/pagination")
    @ResponseBody
    public ResponseEntity<PaginationDto> getPaginationInfos(@RequestParam(required = false) Map<String, String> params) {
        long currentPage = params.get("page") == null ? 1 : Long.parseLong(params.get("page"));
        int countPerPage = params.get("cpp") == null ? DEFAULT_COUNT_PER_PAGE : Integer.parseInt(params.get("cpp"));

        return ResponseEntity.ok(boardService.makePaginationInfo(currentPage, countPerPage));
    }

    @GetMapping("/articles/form")
    public String goWriteView() {
        return "board/form";
    }

    @GetMapping("/articles/{articleId}")
    public String viewArticle(@PathVariable long articleId, Model model) {
        model.addAttribute("article", boardService.findArticleByArticleId(articleId));
        model.addAttribute("comments", boardService.findCommentsByArticleId(articleId));
        return "board/view";
    }

    @GetMapping("/articles/{articleId}/edit")
    @BoardCheck
    public String goModifyArticleView(@PathVariable long articleId, Model model) {
        model.addAttribute("article", boardService.findArticleByArticleId(articleId));
        return "board/edit";
    }

    @PostMapping("/articles")
    public String writeArticle(String title, String writerId, String content) {
        ArticleDto articleDto = ArticleDto.builder()
                .title(title)
                .writerId(writerId)
                .content(content).build();

        boardService.writeArticle(articleDto);

        return "redirect:/board/articles";
    }

    @PostMapping("/articles/{articleId}/comments")
    @ResponseBody
    public ResponseEntity<CommentDto> writeComment(@PathVariable("articleId") long articleId, @RequestBody CommentDto commentDto) {
        return ResponseEntity.ok(boardService.writeComment(articleId, commentDto));
    }

    @PutMapping("/articles/{articleId}")
    @BoardCheck
    public String modifyArticle(@PathVariable long articleId, String title, String content) {
        boardService.modifyArticle(ArticleDto.builder()
                .articleId(articleId)
                .title(title)
                .content(content).build());
        return "redirect:/board/articles/" + articleId;
    }

    @DeleteMapping("/articles/{articleId}")
    @BoardCheck
    public String deleteArticle(@PathVariable long articleId, String writerId) {
        List<CommentDto> allComments = boardService.findCommentsByArticleId(articleId);
        List<CommentDto> writerComments = boardService.findCommentByWriterId(writerId);

        if (allComments.size() != writerComments.size()) {
            throw new NotMyArticleException("해당 게시글에 게시자가 아닌 다른 계정의 댓글이 존재하여 삭제할 수 없습니다.");
        }

        boardService.deleteCommentByArticleId(articleId);
        boardService.deleteArticle(articleId);
        return "redirect:/board/articles";
    }

    @DeleteMapping("/articles/{articleId}/comments/{commentId}")
    @BoardCheck(type = BoardCheck.Type.COMMENT)
    @ResponseBody
    public ResponseEntity<Object> deleteComment(@PathVariable("articleId") long articleId,
                                                @PathVariable("commentId") long commentId) {
        boardService.deleteComment(articleId, commentId);

        return ResponseEntity.ok().build();
    }
}
