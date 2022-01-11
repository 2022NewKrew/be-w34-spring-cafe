package com.kakao.cafe.post.mapper;

import com.kakao.cafe.post.domain.entity.Comment;
import com.kakao.cafe.post.domain.entity.Post;
import com.kakao.cafe.post.presentation.dto.CreatePostRequest;
import com.kakao.cafe.post.presentation.dto.PostDetailDto;
import com.kakao.cafe.post.presentation.dto.PostDto;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class PostMapper {
    private static final int SUMMARY_SIZE = 20;

    public static PostDto toPostDto(Post post) {
        String summary =  post.getContent().length() > SUMMARY_SIZE
                ? post.getContent().substring(0, SUMMARY_SIZE)
                : post.getContent();

        return new PostDto(post.getId(), post.getTitle(), post.getWriterName(), summary);
    }

    public static PostDetailDto toPostDetailDto(Post post){
        List<String> commentStrings = post.getComments()
                .stream()
                .map(Comment::toString)
                .collect(toList());

        return new PostDetailDto(post.getId(), post.getTitle(), post.getContent(), post.getWriterName(), commentStrings);
    }

    public static Post toEntity(CreatePostRequest createPostRequest) {
        String defaultName = "아무개"; // session 적용 전 잠시
        return new Post(createPostRequest.getTitle(), createPostRequest.getContent(), defaultName);
    }
}
