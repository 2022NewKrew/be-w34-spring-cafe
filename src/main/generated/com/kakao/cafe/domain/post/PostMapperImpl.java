package com.kakao.cafe.domain.post;

import com.kakao.cafe.interfaces.common.PostDto;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-01-13T13:19:45+0900",
    comments = "version: 1.5.0.Beta2, compiler: javac, environment: Java 11.0.13 (Eclipse Adoptium)"
)
@Component
public class PostMapperImpl implements PostMapper {

    @Override
    public PostDto toDto(Post arg0) {
        if ( arg0 == null ) {
            return null;
        }

        String writer = null;
        String title = null;
        String body = null;

        writer = arg0.getWriter();
        title = arg0.getTitle();
        body = arg0.getBody();

        PostDto postDto = new PostDto( writer, title, body );

        postDto.setId( arg0.getId() );

        return postDto;
    }

    @Override
    public Post toEntity(PostDto arg0) {
        if ( arg0 == null ) {
            return null;
        }

        Post post = new Post();

        post.setId( arg0.getId() );
        post.setWriter( arg0.getWriter() );
        post.setTitle( arg0.getTitle() );
        post.setBody( arg0.getBody() );

        return post;
    }

    @Override
    public List<PostDto> toDtoList(List<Post> arg0) {
        if ( arg0 == null ) {
            return null;
        }

        List<PostDto> list = new ArrayList<PostDto>( arg0.size() );
        for ( Post post : arg0 ) {
            list.add( toDto( post ) );
        }

        return list;
    }

    @Override
    public List<Post> toEntityList(List<PostDto> arg0) {
        if ( arg0 == null ) {
            return null;
        }

        List<Post> list = new ArrayList<Post>( arg0.size() );
        for ( PostDto postDto : arg0 ) {
            list.add( toEntity( postDto ) );
        }

        return list;
    }

    @Override
    public void updateFromDto(PostDto arg0, Post arg1) {
        if ( arg0 == null ) {
            return;
        }

        if ( arg0.getId() != null ) {
            arg1.setId( arg0.getId() );
        }
        if ( arg0.getWriter() != null ) {
            arg1.setWriter( arg0.getWriter() );
        }
        if ( arg0.getTitle() != null ) {
            arg1.setTitle( arg0.getTitle() );
        }
        if ( arg0.getBody() != null ) {
            arg1.setBody( arg0.getBody() );
        }
    }
}
