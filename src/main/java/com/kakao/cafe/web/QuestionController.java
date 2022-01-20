package com.kakao.cafe.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class QuestionController {
    private final QuestionRepository questionRepository;  // 질문글 DAO
    private final ReplyRepository replyRepository;  // 답글 DAO
    private final Logger logger = LoggerFactory.getLogger(QuestionController.class);

    @Autowired
    public QuestionController(QuestionRepository questionRepository, ReplyRepository replyRepository) {
        this.questionRepository = questionRepository;
        this.replyRepository = replyRepository;
    }

    // 질문글 전체 목록 페이지 요청 url (홈 url)
    // Model 입력값: 질문글 전체 목록
    // 반환 페이지: /index.html
    @GetMapping("/")
    public String getIndex(Model model) {
        logger.info("GET /");
        // 질문글 DAO를 이용해 질문글 전체 목록 조회
        model.addAttribute("question", questionRepository.getQuestionList());
        return "index";
    }

    // 질문글 세부 정보 페이지 요청 url
    // Model 입력값: 1) 질문글 고유 id를 이용해 조회한 질문글
    //             2) 질문글 고유 id를 이용해 조회한 답글 목록
    // 반환 페이지: /qna/show.html
    @GetMapping("/questions/{questionId}")
    public String getQuestion(@PathVariable int questionId, Model model, HttpSession session, HttpServletRequest request)
            throws LoginRequiredException {
        
        logger.info(request.getMethod() + " " + request.getRequestURI());

        User user = (User) session.getAttribute("sessionedUser");
        if (user == null) {
            throw new LoginRequiredException();
        }

        // 질문글 DAO를 이용해 질문글 조회 후 모델에 추가
        model.addAttribute("question", questionRepository.getQuestion(questionId));
        // 답글 DAO를 이용해 답글 목록 조회 후 모델에 추가
        model.addAttribute("reply", replyRepository.getReplyList(questionId));
        return "qna/show";
    }

    // 질문글 작성 페이지 요청 url
    // 반환 페이지: /qna/form.html
    @GetMapping("/questions/form")
    public String getForm(HttpSession session, HttpServletRequest request)
            throws LoginRequiredException {
        
        logger.info(request.getMethod() + " " + request.getRequestURI());

        User user = (User) session.getAttribute("sessionedUser");
        if (user == null) {
            throw new LoginRequiredException();
        }

        return "qna/form";
    }

    // 질문글 작성 실행 요청 url
    // title, contents: form으로부터 받아온 질문글 제목, 내용
    // 반환 페이지: 홈 화면(/)으로 리다이렉트
    @PostMapping("/questions")
    public String postQuestion(String title, String contents, HttpSession session, HttpServletRequest request)
            throws LoginRequiredException {
        
        logger.info(request.getMethod() + " " + request.getRequestURI());

        User user = (User) session.getAttribute("sessionedUser");
        if (user == null) {
            throw new LoginRequiredException();
        }

        // 세션 유저의 ID 정보와 함께 질문글 DAO에 질문글 추가 요청
        questionRepository.addQuestion(user.getId(), title, contents);
        return "redirect:/";
    }

    // 질문글 수정 페이지 요청 url
    // id: 수정할 게시글의 고유 id
    // Model 입력값: id
    // 반환 페이지: /qna/update_form.html
    @GetMapping("/questions/{questionId}/update-form")
    public String getUpdateForm(@PathVariable int questionId, Model model, HttpSession session, HttpServletRequest request)
            throws LoginRequiredException, NoPermissionException {
        
        logger.info(request.getMethod() + " " + request.getRequestURI());

        User user = (User) session.getAttribute("sessionedUser");
        if (user == null) {
            throw new LoginRequiredException();
        }

        // 세션 유저의 ID가 수정할 질문글 작성자 ID와 일치하는지 검사
        if (!user.getId().equals(questionRepository.getQuestion(questionId).getWriterId())) {
            throw new NoPermissionException();
        }

        model.addAttribute("id", questionId);
        return "/qna/update_form";
    }

    // 질문글 수정 실행 요청 url
    // id: 수정할 게시글의 고유 id
    // title, contents: form으로부터 받아온 새로운 제목, 내용
    // 반환 페이지: 홈 화면(/)으로 리다이렉트
    @PutMapping("/questions/{questionId}")
    public String putQuestion(@PathVariable int questionId, String title, String contents, HttpSession session, HttpServletRequest request)
            throws LoginRequiredException {
        
        logger.info(request.getMethod() + " " + request.getRequestURI());

        User user = (User) session.getAttribute("sessionedUser");
        if (user == null) {
            throw new LoginRequiredException();
        }

        // 질문글 DAO 이용해 수정
        questionRepository.updateQuestion(questionId, title, contents);
        return "redirect:/";
    }
    
    // 질문글 삭제 실행 요청 url
    // id: 삭제할 게시글의 고유 id
    // 반환 페이지: 홈 화면(/)으로 리다이렉트
    @DeleteMapping("/questions/{questionId}")
    public String deleteQuestion(@PathVariable int questionId, HttpSession session, HttpServletRequest request)
            throws LoginRequiredException, NoPermissionException {

        logger.info(request.getMethod() + " " + request.getRequestURI());

        User user = (User) session.getAttribute("sessionedUser");
        if (user == null) {
            throw new LoginRequiredException();
        }

        // 세션 유저의 ID가 삭제할 질문글 작성자 ID와 일치하는지 검사
        if (!user.getId().equals(questionRepository.getQuestion(questionId).getWriterId())) {
            throw new NoPermissionException();
        }
        // 질문글 DAO를 이용해 질문글 삭제
        questionRepository.deleteQuestion(questionId);
        return "redirect:/";
    }

    // 답글 작성 실행 요청 url
    // id: 해당 질문글의 고유 id
    // contents: 답글 내용
    // 반환 페이지: 현재 페이지와 같은 페이지인 /questions/{id}로 리다이렉트
    @PostMapping("/questions/{questionId}/reply")
    public String postReply(@PathVariable int questionId, String contents, HttpSession session, HttpServletRequest request)
            throws LoginRequiredException {
        
        logger.info(request.getMethod() + " " + request.getRequestURI());

        User user = (User) session.getAttribute("sessionedUser");
        if (user == null) {
            throw new LoginRequiredException();
        }

        // 답글 DAO를 이용해 답글 추가
        replyRepository.addReply(questionId, user.getId(), contents);
        return "redirect:/questions/{id}";
    }
}
