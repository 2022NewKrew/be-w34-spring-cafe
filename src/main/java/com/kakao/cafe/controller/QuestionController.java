package com.kakao.cafe.controller;

import com.kakao.cafe.common.exception.BaseException;
import com.kakao.cafe.question.Question;
import com.kakao.cafe.question.QuestionService;
import com.kakao.cafe.question.dto.QuestionCreateDto;
import com.kakao.cafe.question.dto.QuestionDto;
import com.kakao.cafe.question.dto.QuestionUpdateDto;
import com.kakao.cafe.user.User;
import com.kakao.cafe.user.dto.UserDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequestMapping("/questions")
@RequiredArgsConstructor
public class QuestionController {

    private final QuestionService questionService;
    private final ModelMapper modelMapper;

    @PostMapping("/create")
    public String insertQuestion(HttpSession session, @ModelAttribute("question") @Valid QuestionCreateDto questionCreateDto, Model model) throws BaseException, SQLException {

        User user = modelMapper.map(getLoginUser(session), User.class);
        Question question = modelMapper.map(questionCreateDto, Question.class);

        question.setMemberId(user.getId());
        question.setWriter(user.getUserId());
        questionService.save(question);

        return "redirect:/";
    }

    @GetMapping
    public String viewQuestionList(HttpSession session, Model model) {

        Long id = getMemberId(session);
        List<QuestionDto> questions = getQuestionDtos(id);

        model.addAttribute("questions", questions);
        model.addAttribute("size", questions.size());

        return "qna/list";

    }

    private List<QuestionDto> getQuestionDtos(Long id) {

        return questionService.findAll()
                .stream()
                .map(q -> {
                    QuestionDto question = modelMapper.map(q, QuestionDto.class);
                    question.setThisIsMine(id.equals(q.getMemberId()));
                    return question;
                })
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public String viewQuestionDetail(HttpSession session, @PathVariable("id") Long id, Model model) {

        Long memberId = getMemberId(session);
        QuestionDto question = modelMapper.map(questionService.findOne(id), QuestionDto.class);

        question.setThisIsMine(memberId.equals(question.getMemberId()));
        model.addAttribute("question", question);

        return "qna/detail";

    }

    @GetMapping("/create")
    public String viewQuestionForm(Model model) {
        return "qna/form";
    }

    @DeleteMapping("/{id}")
    public String deleteQuestion(HttpSession session, @PathVariable("id") Long id, Model model) throws BaseException {

        Long memberId = getMemberId(session);

        questionService.deleteOne(id, memberId);

        return "redirect:/";
    }

    @PutMapping("/{id}")
    public String updateQuestion(HttpSession session, @PathVariable("id") Long id, @Valid @ModelAttribute("question") QuestionUpdateDto questionUpdateDto, Model model) throws BaseException {

        Long memberId = getMemberId(session);
        Question question = new Question();

        question.setId(id);
        question.setMemberId(memberId);
        question.setTitle(questionUpdateDto.getTitle());
        question.setContents(questionUpdateDto.getContents());

        questionService.updateOne(question);

        return "redirect:/";
    }

    private Long getMemberId(HttpSession session) {

        UserDto loginUser = getLoginUser(session);
        return loginUser == null ? -1L : loginUser.getId();
    }

    private UserDto getLoginUser(HttpSession session) {
        return (UserDto) session.getAttribute("loginUser");
    }
}
