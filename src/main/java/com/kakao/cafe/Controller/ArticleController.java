package com.kakao.cafe.Controller;


import com.kakao.cafe.Model.ArticleDTO;
import com.kakao.cafe.Model.ArticleList;
import com.kakao.cafe.Model.CommentDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ArticleController {
    Logger logger = LoggerFactory.getLogger(ArticleController.class);
    private ArticleList articleList = new ArticleList();

    @PostMapping("/article")
    public String post(ArticleDTO articleDTO) {
        articleList.add(articleDTO);
        logger.info("{}(title) was posted by {}(author)", articleDTO.getTitle(), articleDTO.getAuthor());
        return "redirect:/";
    }

    @GetMapping("/")
    public String home(Model model) {
        logger.info("GET /");
        logger.info("{}", articleList);

        model.addAttribute("ArticleList", articleList);
        return "/index";
    }

    @GetMapping("/article/{articleIdx}")
    public String getArticle(@PathVariable("articleIdx") int articleIdx,
                             Model model){
        logger.info("GET /article/{} : View article({})", articleIdx, articleIdx);

        ArticleDTO articleDTO = articleList.getArticleList().get(articleIdx - 1);
        model.addAttribute("article", articleDTO);
        return "/post/show";
    }

    @PostMapping("/comment/{articleIdx}")
    public String postComment(@PathVariable("articleIdx") int articleIdx,
                             CommentDTO commentDTO,
                             Model model){
        logger.info("POST /comment/{} : Create a comment on article({})", articleIdx, articleIdx);

        ArticleDTO articleDTO = articleList.getArticleList().get(articleIdx - 1);
        articleDTO.addComment(commentDTO);
        model.addAttribute("article", articleDTO);
        return "redirect:/article/{articleIdx}";
    }
}
