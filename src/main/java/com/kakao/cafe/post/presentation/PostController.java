package com.kakao.cafe.post.presentation;

import com.kakao.cafe.post.application.*;
import com.kakao.cafe.post.domain.entity.Comment;
import com.kakao.cafe.post.domain.entity.Post;
import com.kakao.cafe.post.presentation.dto.CommentRequest;
import com.kakao.cafe.post.presentation.dto.PostDetailDto;
import com.kakao.cafe.post.presentation.dto.PostRequest;
import com.kakao.cafe.user.application.SearchUserService;
import com.kakao.cafe.user.domain.entity.User;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Objects;


@Controller
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {
    private static final Logger logger = LoggerFactory.getLogger(PostController.class);

    private final SearchUserService searchUserService;

    private final SearchPostService postInfoService;
    private final WritePostService writePostService;
    private final UpdatePostService updatePostService;
    private final DeletePostService deletePostService;

    private final AddCommentService commentService;
    private final DeleteCommentService deleteCommentService;

    private final ModelMapper modelMapper;

    @GetMapping("/form")
    public String getPostForm(){
        return "post/form";
    }

    @GetMapping("/updateForm/{postId}")
    public String getUpdateForm(@PathVariable Long postId, Model model){
        model.addAttribute("postId", postId);
        return "post/updateForm";
    }

    @GetMapping("/{id}")
    public String getPostDetail(@PathVariable Long id, Model model, HttpSession session){
        Post post = postInfoService.getPost(id);
        PostDetailDto postDto = modelMapper.map(post, PostDetailDto.class);

        String userId = getSessionUserId(session);
        logger.info("사용자 {}가 id가 {}인 게시글을 조회하였습니다.", userId, id);

        model.addAttribute("post", postDto);
        return "post/info";
    }

    @PostMapping("")
    public String createPost(PostRequest postRequest, HttpSession session){
        User user = searchUserService.getUser(getSessionUserId(session));
        postRequest.setWriterName(user.getUserInfo().getName());

        writePostService.save(modelMapper.map(postRequest, Post.class));
        logger.info("사용자 {}가 제목이 {}인 게시글을 작성하였습니다.", user.getUserInfo().getName(), postRequest.getTitle());

        return "redirect:/";
    }

    @PostMapping("/{id}/comment")
    public String addComment(@PathVariable Long id, CommentRequest commentRequest, HttpSession session){
        User user = searchUserService.getUser(getSessionUserId(session));
        commentRequest.setWriterName(user.getUserInfo().getName());

        commentService.addComment(id, modelMapper.map(commentRequest, Comment.class));
        logger.info("사용자 {}가 id가 {}인 게시글에 댓글을 달았습니다.", user.getUserId(), id);

        return "redirect:";
    }

    @PutMapping("/{id}")
    public String updateComment(@PathVariable Long id, String newContent, HttpSession session){
        updatePostService.update(id, newContent);
        String userId = getSessionUserId(session);
        logger.info("사용자 {}가 id가 {}인 게시글의 내용을 바꿨습니다.", userId, id);

        return String.format("redirect:/posts/%d", id);
    }

    @DeleteMapping("/{id}")
    public String deletePost(@PathVariable Long id, HttpSession session){
        User user = searchUserService.getUser(getSessionUserId(session));
        deletePostService.softDelete(id, user.getUserInfo().getName());
        logger.info("사용자 {}가 id가 {}인 게시글을 삭제하였습니다.", user.getUserId(), id);

        return "redirect:/";
    }

    @DeleteMapping("/{postId}/comment/{commentId}")
    public String deleteComment(@PathVariable Long postId, @PathVariable Long commentId, HttpSession session){
        deleteCommentService.deleteComment(commentId);
        final String userId = getSessionUserId(session);
        logger.info("사용자 {}가 id가 {}인 게시글의 댓글 {}을 삭제하였습니다.", userId, postId, commentId);

        return String.format("redirect:/posts/%d", postId);
    }

    private String getSessionUserId(HttpSession session){
        return Objects.requireNonNull(
                (String)session.getAttribute("userId"), "로그인을 먼저 하세요");
    }
}
