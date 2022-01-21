package com.kakao.cafe.domain.post;

import com.kakao.cafe.interfaces.common.PostDto;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-01-21T18:01:58+0900",
    comments = "version: 1.5.0.Beta2, compiler: javac, environment: Java 11.0.13 (Eclipse Adoptium)"
)
@Component
public class PostMapperImpl implements PostMapper {

    @Override
    public Post toEntity(PostDto postDto) {
        if ( postDto == null ) {
            return null;
        }

        Post.PostBuilder post = Post.builder();

        post.writer( postDto.getWriter() );
        post.title( postDto.getTitle() );
        post.body( postDto.getBody() );

        return post.build();
    }
}
