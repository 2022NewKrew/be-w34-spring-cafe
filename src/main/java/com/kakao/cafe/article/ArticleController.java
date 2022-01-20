package com.kakao.cafe.article;

import com.kakao.cafe.configures.web.ArticleRequestResolver;
import com.kakao.cafe.reply.Reply;
import com.kakao.cafe.reply.ReplyDto;
import com.kakao.cafe.reply.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/qna")
public class ArticleController {

    private final ArticleService articleService;

    private final ReplyService replyService;

    @Autowired
    public ArticleController(ArticleService articleService, ReplyService replyService) {
        this.articleService = articleService;
        this.replyService = replyService;
    }

    @GetMapping(path = "/form")
    public String createArticleForm() {
        return "qna/form";
    }

    @GetMapping(path = "/show/{seq}")
    public String showArticle(@PathVariable long seq, @ArticleRequestResolver ArticleRequest articleRequest, Model model) {
        Article article = articleService.findBySeq(seq);
        model.addAttribute("article", new ArticleDto(article, articleRequest.getUserSeq()));
        List<Reply> replys = replyService.findByArticleSeq(article.getSeq());
        model.addAttribute("replysCount", replys.size());
        model.addAttribute("replys", replys.stream().map(reply -> new ReplyDto(reply, articleRequest.getUserSeq())).collect(Collectors.toList()));
        return "qna/show";
    }

    @PostMapping(path = "/create")
    public String createArticle(@ArticleRequestResolver ArticleRequest articleRequest) {
        articleService.createArticle(Article.builder()
                .userSeq(articleRequest.getUserSeq())
                .writer(articleRequest.getWriter())
                .title(articleRequest.getTitle())
                .content(articleRequest.getContents())
                .build());
        return "redirect:/";
    }

    @GetMapping(path = "/update/{seq}")
    public String updateArticlePage(@PathVariable long seq, Model model) {
        Article article = articleService.findBySeq(seq);
        model.addAttribute("article", new ArticleDto(article));
        return "qna/updateForm";
    }

    @PutMapping(path = "/update/{seq}")
    public String updateArticle(@PathVariable long seq, @ArticleRequestResolver ArticleRequest articleRequest) {
        articleService.updateArticle(Article.builder()
                .seq(seq)
                .userSeq(articleRequest.getUserSeq())
                .writer(articleRequest.getWriter())
                .title(articleRequest.getTitle())
                .content(articleRequest.getContents())
                .build());
        return "redirect:/qna/show/" + seq;
    }

    @DeleteMapping(path = "/delete/{seq}")
    public String deleteArticle(@PathVariable long seq, @ArticleRequestResolver ArticleRequest articleRequest) {
        articleService.deleteArticle(Article.builder()
                .seq(seq).userSeq(articleRequest.getUserSeq()).build());
        return "redirect:/";
    }
}
