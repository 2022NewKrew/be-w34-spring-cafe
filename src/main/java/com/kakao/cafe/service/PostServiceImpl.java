package com.kakao.cafe.service;

import com.kakao.cafe.domain.Post;
import com.kakao.cafe.domain.Member;
import com.kakao.cafe.dto.PostCreateDto;
import com.kakao.cafe.dto.PostDetailDto;
import com.kakao.cafe.dto.PostListItemDto;
import com.kakao.cafe.repository.PostRepository;
import com.kakao.cafe.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;
    private final MemberRepository memberRepository;

    @Autowired
    public PostServiceImpl(@Qualifier("postRepositoryJDBC") PostRepository postRepository,
                           @Qualifier("memberRepositoryJDBC") MemberRepository memberRepository) {
        this.postRepository = postRepository;
        this.memberRepository = memberRepository;
    }

    @Override
    public void create(PostCreateDto postCreateDto, HttpSession session) {
        Member loginMember = (Member)session.getAttribute("sessionedUser");

        Post post = Post.of(postCreateDto, loginMember);
        postRepository.save(post);
    }

    @Override
    public List<PostListItemDto> getList() {
        Optional<List<Post>> postList = postRepository.findAll();

        return postList.map(posts -> posts
                .stream()
                .map(PostListItemDto::of)
                .collect(Collectors.toList())).orElse(null);
    }

    @Override
    public PostDetailDto get(int questionId) {
        Post post = postRepository.findById(questionId)
                .orElseThrow(() -> new IllegalArgumentException("post not found"));
        post.updateViewCount();
        postRepository.update(post);
        return PostDetailDto.of(post);
    }

    @Override
    public void delete(int questionId, HttpSession session) {
        Member loginMember = (Member)session.getAttribute("sessionedUser");

        Post post = postRepository.findById(questionId)
                .orElseThrow(() -> new IllegalArgumentException("post not found"));

        if (post.getWriter().getId() != loginMember.getId())
            throw new IllegalArgumentException("Access denied");

        postRepository.remove(post);
    }
}
