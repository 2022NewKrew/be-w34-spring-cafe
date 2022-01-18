package com.kakao.cafe.controller;

import com.kakao.cafe.domain.Article;
import com.kakao.cafe.domain.User;
import com.kakao.cafe.dto.ArticleDTO;
import com.kakao.cafe.dto.UserDTO;
import com.kakao.cafe.service.ArticleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@RequestMapping("/article")
@RequiredArgsConstructor
@Controller
@Slf4j
public class ArticleController {
    private final ArticleService articleService;

    @GetMapping()
    public String articleList(@RequestParam(value="error", required = false, defaultValue = "none") String error,
                                Model model) {
        if (!error.equals("none")) {
            model.addAttribute("error", true);
            model.addAttribute("errorMessage", error);
        }
        model.addAttribute("articles", articleService.findAllDTO());
        return "article/list";
    }

    @GetMapping("/post")
    public String articlePostForm(Model model, HttpSession session) {
        if(!isLoggedIn(session)) return "redirect:/article?error=NO PERMISSION";
        model.addAttribute("article", new ArticleDTO());
        return "article/form";
    }

    @PostMapping("/post")
    public String articlePost(ArticleDTO articleDTO, HttpSession session) {
        if (!isLoggedIn(session)) return "redirect:/article?error=NO PERMISSION";
        if (articleDTO.getTitle().length() == 0) return "redirect:/article?error=NO TITLE";
        UserDTO userDTO = ((User)session.getAttribute("sessionedUser")).getDTO();
        articleDTO.setAuthor(userDTO);
        articleService.join(articleDTO);
        return "redirect:/article";
    }

    @GetMapping("/{key}")
    public String articleShow(@PathVariable("key") Long key, Model model, HttpSession session) {
        if (!isLoggedIn(session)) {
            return "redirect:/article?error=NO PERMISSION";
        }
        Optional<ArticleDTO> articleDTO = articleService.findByKeyDTO(key);
        if (articleDTO.isEmpty()) {
            return "redirect:/article?error=Article doesn't exist";
        }
        model.addAttribute("article", articleDTO.get());
        return "article/show";
    }

    @PutMapping("/{key}")
    public String articleUpdate(@PathVariable("key") Long key, ArticleDTO articleDTO, HttpSession session) {
        if (isUserHasPermission(session, key).isEmpty()) {
            return "redirect:/article?error=NO PERMISSION";
        }
        articleService.update(key, articleDTO);
        return "redirect:/article/" + key;
    }

    @DeleteMapping("/{key}")
    public String articleDelete(@PathVariable("key") Long key, HttpSession session) {
        if (isUserHasPermission(session, key).isEmpty()) {
            return "redirect:/article?error=NO PERMISSION";
        }
        articleService.delete(key);
        return "redirect:/article";
    }

    @GetMapping("/{key}/update")
    public String articleUpdateForm(@PathVariable("key") Long key, Model model, HttpSession session) {
        Optional<ArticleDTO> articleDTO = isUserHasPermission(session, key);
        if (articleDTO.isEmpty()) {
            return "redirect:/article?error=NO PERMISSION";
        }
        model.addAttribute("article", articleDTO.get());
        return "article/update";
    }

    public boolean isLoggedIn(HttpSession session) {
        Object sessionedUser = session.getAttribute("sessionedUser");
        if (sessionedUser == null) {
            return false;
        }
        return true;
    }

    public Optional<ArticleDTO> isUserHasPermission(HttpSession session, Long key) {
        Object sessionedUser = session.getAttribute("sessionedUser");
        if (sessionedUser == null) {
            return Optional.empty();
        }
        Optional<ArticleDTO> articleDTO = articleService.findByKeyDTO(key);
        if (articleDTO.isEmpty() || articleDTO.get().getAuthor().getKey() != ((User)sessionedUser).getKey()) {
            return Optional.empty();
        }
        return articleDTO;
    }
}
