package com.kakao.cafe.post.data;

import com.kakao.cafe.post.domain.entity.Comment;
import com.kakao.cafe.post.domain.entity.Post;
import com.kakao.cafe.post.presentation.dto.CommentRequest;
import com.kakao.cafe.post.presentation.dto.PostRequest;
import org.junit.jupiter.params.provider.Arguments;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class PostsData {
    public static Stream<Arguments> getCommentRequestStream(){
        return getIdToComments().values().stream()
                .flatMap(List::stream)
                .map(comment -> new CommentRequest(comment.getContent()))
                .map(Arguments::of);
    }

    public static Stream<Arguments> getPostRequestStream(){
        return getPostList().stream()
                .map(post -> new PostRequest(post.getTitle(), post.getContent()))
                .map(Arguments::of);
    }

    public static Stream<Arguments> getPostStream(){
        return getPostList().stream().map(Arguments::of);
    }

    public static List<Post> getPostList(){
        final Map<Long, List<Comment>> idToComments = getIdToComments();

        return IntStream.iterate(1, i->i+1)
                .limit(5)
                .mapToObj(num -> new Post(1000L+num, String.format("title%d", num),
                        String.format("content%d", num), String.format("writer%d", num),
                        LocalDateTime.now(), idToComments.get(1000L+num))
                ).collect(toList());
    }

    public static Map<Long,List<Comment>> getIdToComments(){
        return Map.of(
                1001L, List.of(
                        new Comment("commenter1", "comment1"),
                        new Comment("commenter2", "comment2"),
                        new Comment("commenter3", "comment3")
                ),
                1002L, List.of(
                        new Comment("commenter1", "good"),
                        new Comment("commenter2", "job"),
                        new Comment("commenter3", "cool")
                ),
                1003L, List.of(
                        new Comment("asdf", "LGTM!"),
                        new Comment("hello", "LGTM!"),
                        new Comment("good", "WOW~")
                ),
                1004L, List.of(
                        new Comment("developer1", "good"),
                        new Comment("developer2", "job"),
                        new Comment("developer3", "good")
                ),
                1005L, List.of(
                        new Comment("commenter4", "comment4"),
                        new Comment("commenter5", "comment5"),
                        new Comment("commenter6", "comment6")
                )
        );
    }
}
