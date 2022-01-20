package com.kakao.cafe.controller;

import com.kakao.cafe.common.exception.BaseException;
import com.kakao.cafe.controller.common.SessionLoginUser;
import com.kakao.cafe.question.Question;
import com.kakao.cafe.question.QuestionService;
import com.kakao.cafe.question.dto.QuestionCreateDto;
import com.kakao.cafe.question.dto.QuestionDto;
import com.kakao.cafe.question.dto.QuestionUpdateDto;
import com.kakao.cafe.reply.Reply;
import com.kakao.cafe.reply.ReplyService;
import com.kakao.cafe.reply.dto.ReplyCreateDto;
import com.kakao.cafe.reply.dto.ReplyDto;
import com.kakao.cafe.user.User;
import com.kakao.cafe.user.dto.UserDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequestMapping("/questions")
@RequiredArgsConstructor
public class QuestionController {

    private static final Long NOT_FOUND_MEMBER_ID = -1L;
    private final QuestionService questionService;
    private final ReplyService replyService;
    private final ModelMapper modelMapper;
    private final SessionLoginUser<UserDto> sessionLoginUser;

    @DeleteMapping("/{id}")
    public String deleteQuestion(@PathVariable("id") Long id) throws BaseException {

        Long memberId = getMemberId();

        questionService.deleteOne(id, memberId);

        return "redirect:/questions";
    }

    @PostMapping("/create")
    public String insertQuestion(@ModelAttribute("question") @Valid QuestionCreateDto questionCreateDto) throws BaseException, SQLException {

        User user = modelMapper.map(sessionLoginUser.getLoginUser(), User.class);
        Question question = modelMapper.map(questionCreateDto, Question.class);

        question.setMemberId(user.getId());
        question.setWriter(user.getUserId());
        questionService.save(question);

        return "redirect:/questions";
    }

    @PutMapping("/update/{id}")
    public String updateQuestion(@PathVariable("id") Long id, @Valid @ModelAttribute("question") QuestionUpdateDto questionUpdateDto, Model model) throws BaseException {

        Long memberId = getMemberId();
        Question question = modelMapper.map(questionUpdateDto, Question.class);

        question.setId(id);
        question.setMemberId(memberId);
        questionService.updateOne(question);

        return String.format("redirect:/questions/%d", id);
    }

    @GetMapping("/{id}")
    public String viewQuestionDetail(@PathVariable("id") Long id, Model model) {

        Long memberId = getMemberId();
        QuestionDto question = QuestionDto.of(questionService.findOne(id), memberId);

        model.addAttribute("question", question);

        List<Reply> replies = replyService.findAllAsQuestionId(id);

        if (replies.size() != 0) {
            List<ReplyDto> replyDtos = replies
                    .stream().map(
                            r -> {
                                ReplyDto reply = modelMapper.map(r, ReplyDto.class);
                                reply.setThisIsMine(memberId.equals(r.getMemberId()));
                                return reply;
                            }
                    )
                    .collect(Collectors.toList());

            model.addAttribute("replySize", replies.size());
            model.addAttribute("replies", replyDtos);
        }


        return "qna/detail";
    }

    @GetMapping("/create")
    public String viewQuestionForm() {
        return "qna/form";
    }

    @GetMapping
    public String viewQuestionList(Model model) {

        Long memberId = getMemberId();
        List<QuestionDto> questions = QuestionDto.of(questionService.findAll(), memberId);

        model.addAttribute("questions", questions);
        model.addAttribute("size", questions.size());

        return "qna/list";
    }

    @GetMapping("/update/{id}")
    public String viewUpdateQuestionForm(@PathVariable("id") Long id, Model model) throws BaseException {

        Question origin = questionService.findOne(id);
        QuestionUpdateDto question = modelMapper.map(origin, QuestionUpdateDto.class);
        UserDto loginUser = sessionLoginUser.getLoginUser();

        if ((origin != null) && !origin.getMemberId().equals(loginUser.getId())) {
            throw new BaseException("게시글의 권한이 없는 사용자 입니다.");
        }

        model.addAttribute("question", question);

        return "qna/update_form";
    }

    @PostMapping("/{questionId}/answers")
    public String insertReply(@PathVariable Long questionId, @ModelAttribute("reply") ReplyCreateDto replyCreateDto) throws SQLException {

        Reply reply = new Reply();

        reply.setComment(replyCreateDto.getComment());
        reply.setQuestionId(questionId);
        reply.setMemberId(getMemberId());
        reply.setWriter(getUserId());

        replyService.save(reply);

        return String.format("redirect:/questions/%d", questionId);
    }

    @DeleteMapping("/{questionId}/answers/{id}")
    public String deleteReply(@PathVariable Long questionId, @PathVariable Long id, @ModelAttribute("reply") ReplyCreateDto replyCreateDto) throws BaseException {

        replyService.deleteOne(id, getMemberId(), questionId);

        return String.format("redirect:/questions/%d", questionId);
    }

    private Long getMemberId() {
        UserDto loginUser = sessionLoginUser.getLoginUser();
        return loginUser == null ? NOT_FOUND_MEMBER_ID : loginUser.getId();
    }

    private String getUserId() {
        UserDto loginUser = sessionLoginUser.getLoginUser();
        return loginUser.getUserId();
    }

}
