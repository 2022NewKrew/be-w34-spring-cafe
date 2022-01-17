package com.kakao.cafe.controller;

import com.kakao.cafe.common.exception.BaseException;
import com.kakao.cafe.question.Question;
import com.kakao.cafe.question.QuestionService;
import com.kakao.cafe.question.dto.QuestionCreateDto;
import com.kakao.cafe.question.dto.QuestionDto;
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
    public String insertQuestion(HttpServletRequest request, @ModelAttribute("question") @Valid QuestionCreateDto questionCreateDto, Model model) throws BaseException {

        HttpSession httpSession = request.getSession();
        User user = modelMapper.map(httpSession.getAttribute("loginUser"), User.class);
        Question question = modelMapper.map(questionCreateDto, Question.class);

        question.setMemberId(user.getId());
        question.setWriter(user.getUserId());

        try {
            questionService.save(question);
        } catch (SQLException e) {
            log.error("QUESTION TABLE SAVE 실패 SQLState : {}", e.getSQLState());
            throw new BaseException("게시글 작성 실패");

        }

        return "redirect:/";
    }

    @GetMapping
    public String viewQuestionList(HttpSession session, Model model) {

        UserDto loginUser = (UserDto) session.getAttribute("loginUser");
        Long id = loginUser == null ? -1L : loginUser.getId();
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

        UserDto loginUser = (UserDto) session.getAttribute("loginUser");
        Long memberId = loginUser == null ? -1L : loginUser.getId();
        QuestionDto question = modelMapper.map(questionService.findOne(id), QuestionDto.class);

        question.setThisIsMine(memberId.equals(question.getMemberId()));
        model.addAttribute("question", question);

        return "qna/detail";

    }

    @GetMapping("/create")
    public String viewQuestionForm(Model model) {
        return "qna/form";
    }
}
