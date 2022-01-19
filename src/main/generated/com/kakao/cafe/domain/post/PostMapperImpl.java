package com.kakao.cafe.domain.post;

import com.kakao.cafe.interfaces.common.PostDto;
import java.time.LocalDateTime;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-01-19T19:30:01+0900",
    comments = "version: 1.5.0.Beta2, compiler: javac, environment: Java 11.0.13 (Eclipse Adoptium)"
)
@Component
public class PostMapperImpl implements PostMapper {

    @Override
    public Post toEntity(PostDto post) {
        if ( post == null ) {
            return null;
        }

        Post.PostBuilder post1 = Post.builder();

        post1.writer( post.getWriter() );
        post1.title( post.getTitle() );
        post1.body( post.getBody() );
        if ( post.getCreatedAt() != null ) {
            post1.createdAt( LocalDateTime.parse( post.getCreatedAt() ) );
        }
        post1.isRemoved( post.getIsRemoved() );

        return post1.build();
    }
}
