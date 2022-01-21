package com.kakao.cafe.controller;

import com.kakao.cafe.constants.Constants;
import com.kakao.cafe.controller.dto.request.ArticleRegisterRequestDto;
import com.kakao.cafe.controller.dto.request.ArticleUpdateRequestDto;
import com.kakao.cafe.controller.dto.request.ReplyRegisterRequestDto;
import com.kakao.cafe.controller.dto.response.ArticleQueryDetailResponseDto;
import com.kakao.cafe.controller.dto.response.ArticleUpdateFormResponseDto;
import com.kakao.cafe.controller.dto.session.UserLoginSession;
import com.kakao.cafe.controller.validator.OwnershipValidator;
import com.kakao.cafe.domain.Article;
import com.kakao.cafe.domain.Reply;
import com.kakao.cafe.service.ArticleService;
import com.kakao.cafe.service.dto.ArticleUpdateDto;
import com.kakao.cafe.service.dto.ReplyRegisterDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/articles")
@RequiredArgsConstructor
@Slf4j
public class ArticleController {

    private final ArticleService articleService;
    private final OwnershipValidator ownershipValidator;

    @PostMapping
    public String register(@Validated @ModelAttribute ArticleRegisterRequestDto articleRegisterRequestDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new IllegalArgumentException();
        }
        articleService.register(articleRegisterRequestDto);
        return "redirect:/";
    }

    @PostMapping("/{id}/replys")
    public String registerReply(@PathVariable Long id, ReplyRegisterRequestDto replyRegisterRequestDto) {
        articleService.registerReply(new ReplyRegisterDto(id, replyRegisterRequestDto));

        return "redirect:/articles/" + id;
    }

    @DeleteMapping("/{articleId}/replys/{replyId}")
    public String deleteReply(@PathVariable Long articleId, @PathVariable Long replyId,
                              @SessionAttribute(name = Constants.loginUser) UserLoginSession userLoginSession) {
        Reply reply = articleService.findReplyById(replyId);
        ownershipValidator.validate(userLoginSession.getUserId(), reply.getWriterId());
        articleService.deleteReplyById(replyId);

        return "redirect:/articles/" + articleId;
    }

    @GetMapping("/{id}")
    public String getArticle(@PathVariable("id") Long id, Model model) {
        Article article = articleService.findById(id);
        model.addAttribute("article", new ArticleQueryDetailResponseDto(article));
        return "article/show";
    }

    @PutMapping("/{id}")
    public String update(@PathVariable Long id, @ModelAttribute ArticleUpdateRequestDto articleUpdateRequestDto,
                         @SessionAttribute(name = Constants.loginUser) UserLoginSession userLoginSession) {
        ownershipValidator.validate(userLoginSession.getUserId(), articleUpdateRequestDto.getWriterId());
        articleService.update(new ArticleUpdateDto(id, articleUpdateRequestDto));
        return "redirect:/articles/" + id;
    }

    @GetMapping("/{id}/updateForm")
    public String getUpdateForm(@PathVariable Long id, Model model,
                                @SessionAttribute(name = Constants.loginUser) UserLoginSession userLoginSession) {
        Article foundArticle = articleService.findById(id);
        ownershipValidator.validate(userLoginSession.getUserId(), foundArticle.getWriter().getUserId());
        ArticleUpdateFormResponseDto articleUpdateFormResponseDto = new ArticleUpdateFormResponseDto(id, foundArticle.getWriter().getUserId());
        model.addAttribute("article", articleUpdateFormResponseDto);
        return "article/updateForm";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id, @SessionAttribute(name = Constants.loginUser) UserLoginSession userLoginSession) {

        Article foundArticle = articleService.findById(id);
        ownershipValidator.validate(userLoginSession.getUserId(), foundArticle.getWriter().getUserId());
        articleService.deleteById(id);
        return "redirect:/";
    }
}
