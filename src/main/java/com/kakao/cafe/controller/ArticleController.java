package com.kakao.cafe.controller;

import com.kakao.cafe.dto.ArticleDTO.Create;
import com.kakao.cafe.dto.ArticleDTO.Result;
import com.kakao.cafe.persistence.model.AuthInfo;
import com.kakao.cafe.service.ArticleService;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequiredArgsConstructor
public class ArticleController {

    private static final Logger logger = LoggerFactory.getLogger(ArticleController.class);

    private final ArticleService articleService;

    @PostMapping("/articles")
    public String create(@ModelAttribute @Validated Create createDTO, HttpServletRequest request,
        BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            bindingResult.getFieldErrors()
                .forEach(fieldError -> logger.error("Caused Field : {}, Message : {}",
                    fieldError.getField(),
                    fieldError.getDefaultMessage()));
            return "redirect:/articles/form-failed";
        }

        HttpSession session = request.getSession();
        AuthInfo authInfo = (AuthInfo) session.getAttribute("auth");
        if (authInfo == null) {
            return "redirect:/articles/form-failed";
        }

        articleService.create(createDTO, authInfo);

        return "redirect:/";
    }

    @GetMapping("/")
    public ModelAndView readAll(Map<String, Object> model) {
        List<Result> resultDTOs = articleService.readAll();
        model.put("articles", resultDTOs);

        return new ModelAndView("index", model);
    }

    @GetMapping("/articles/{articleId}")
    public ModelAndView read(Map<String, Object> model,
        @PathVariable Long articleId) {
        Result resultDTO = articleService.readById(articleId);
        model.put("article", resultDTO);

        return new ModelAndView("qna/show", model);
    }
}
