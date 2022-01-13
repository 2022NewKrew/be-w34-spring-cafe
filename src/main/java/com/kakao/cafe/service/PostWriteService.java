package com.kakao.cafe.service;

import com.kakao.cafe.domain.post.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostWriteService {

    private final UserService userService;
    private final PostService postService;

    @Autowired
    public PostWriteService(UserService userService, PostService postService) {
        this.userService = userService;
        this.postService = postService;
    }

    public void insertPost(Post post) {
        userService.findById(post.getWriter());
        postService.insert(post);
    }
}
