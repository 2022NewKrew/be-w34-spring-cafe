package com.kakao.cafe.controller;

import com.kakao.cafe.domain.Post;
import com.kakao.cafe.domain.Reply;
import com.kakao.cafe.domain.User;
import com.kakao.cafe.form.ReplyCreationForm;
import com.kakao.cafe.service.ReplyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.Optional;

@Controller
public class ReplyController {

    private final ReplyService replyService;
    Logger logger = LoggerFactory.getLogger(ReplyController.class);

    public ReplyController(ReplyService replyService) {
        this.replyService = replyService;
    }

    @PostMapping("/post/{ID}/reply")
    public String createReply(@PathVariable int ID, HttpSession session, ReplyCreationForm form){
        logger.info("POST /post/{}/reply : {}게시글 댓글",ID,ID);
        Object value = session.getAttribute("sessionedUser");
        User user = (User)value;
        Reply reply = new Reply();
        reply.setWriter(user.getNickname());
        reply.setPostid(ID);
        reply.setContent(form.getContent());
        reply.setCreateddate(LocalDate.now());
        reply.setUserid(user.getUserId());
        replyService.create(reply);
        return "redirect:/post/"+ID;
    }

    @DeleteMapping("/delete/{postID}/{ID}")
    public String deleteReply(@PathVariable int postID, @PathVariable int ID, HttpSession session){
        logger.info("DELETE /delete/{}/{} : {}게시글 {}댓글 삭제",postID,ID,postID,ID);
        Object value = session.getAttribute("sessionedUser");
        if(value!=null){
            User user = (User)value;
            Optional<Reply> reply = replyService.findById(ID);
            try{
                Reply r = reply.get();
                replyService.delete(r);
                return "redirect:/post/"+postID;
            }catch(Exception e){
                logger.info("null reply error");
                return "user/unkown-user";
            }

        } else{
            return "user/unknown-user";
        }
    }
}
