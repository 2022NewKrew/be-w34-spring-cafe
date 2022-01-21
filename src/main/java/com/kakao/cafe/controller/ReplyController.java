package com.kakao.cafe.controller;

import com.kakao.cafe.dto.ReplyDTO.Create;
import com.kakao.cafe.dto.ReplyDTO.Result;
import com.kakao.cafe.dto.ReplyDTO.Update;
import com.kakao.cafe.error.exception.BindingException;
import com.kakao.cafe.persistence.model.AuthInfo;
import com.kakao.cafe.service.ReplyService;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequiredArgsConstructor
@RequestMapping("/articles/{articleId}/replies")
public class ReplyController {

    private final ReplyService replyService;

    @PostMapping
    public String create(@PathVariable Long articleId, @ModelAttribute @Validated Create createDTO,
        HttpServletRequest request, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BindingException(bindingResult);
        }

        AuthInfo authInfo = (AuthInfo) request.getSession().getAttribute("auth");

        replyService.create(authInfo, articleId, createDTO);
        return "redirect:/articles/" + articleId;
    }

    @GetMapping
    public ModelAndView readAll(@PathVariable Long articleId,
        Map<String, Object> model) {
        List<Result> resultDTOs = replyService.readByArticleId(articleId);
        model.put("replies", resultDTOs);
        return new ModelAndView("reply/show", model);
    }

    @GetMapping("/edit/{id}")
    public ModelAndView editableRead(@PathVariable Long articleId, @PathVariable Long id,
        Map<String, Object> model) {
        Result resultDTO = replyService.readById(id);
        model.put("reply", resultDTO);
        return new ModelAndView("reply/edit", model);
    }

    @PutMapping("/{id}")
    public String update(@PathVariable Long articleId, @PathVariable Long id,
        @ModelAttribute @Validated Update updateDTO, HttpServletRequest request,
        BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BindingException(bindingResult);
        }

        AuthInfo authInfo = (AuthInfo) request.getSession().getAttribute("auth");
        replyService.update(authInfo, id, updateDTO);

        return "redirect:/articles/" + articleId;
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long articleId, @PathVariable Long id,
        HttpServletRequest request) {
        AuthInfo authInfo = (AuthInfo) request.getSession().getAttribute("auth");
        replyService.delete(authInfo, id);

        return "redirect:/articles/" + articleId;
    }
}
