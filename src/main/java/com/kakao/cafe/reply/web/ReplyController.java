package com.kakao.cafe.reply.web;


import com.kakao.cafe.reply.service.ReplyService;
import com.kakao.cafe.reply.web.dto.ReplySaveDto;
import com.kakao.cafe.user.domain.User;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/replies")
public class ReplyController {

    private final ReplyService replyService;
    private static final String SESSION_NAME = "sessionedUser";

    @PostMapping
    public String replyAdd(@Valid ReplySaveDto replySaveDto, HttpSession httpSession) {
        log.info("댓글 생성 요청" + replySaveDto.getContents() + " " + replySaveDto.getArticle());
        replySaveDto.setWriter(findUserId(httpSession));
        replyService.addReply(replySaveDto);
        return "redirect:/articles/" + replySaveDto.getArticle();
    }

    @DeleteMapping("/{id}")
    public String replyRemove(@PathVariable("id") int id, HttpSession httpSession) {
        if (!validate(httpSession, id)) {
            // 나중에 인터셉터로 처리하기?
            return "redirect:/unauthorized";
        }
        int article = replyService.findReplyArticle(id);
        replyService.removeReply(id);
        log.info("댓글 삭제 완료");
        return "redirect:/articles/" + article;
    }

    private String findUserId(HttpSession httpSession) {
        return ((User) httpSession.getAttribute(SESSION_NAME)).getUserId();
    }

    private boolean validate(HttpSession httpSession, int id) {
        return findUserId(httpSession)
            .equals(replyService.findReplyWriter(id));
    }
}
