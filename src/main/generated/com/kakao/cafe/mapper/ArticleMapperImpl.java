package com.kakao.cafe.mapper;

import com.kakao.cafe.dto.ArticleDto;
import com.kakao.cafe.dto.ArticleDto.ArticleDtoBuilder;
import com.kakao.cafe.entity.ArticleEntity;
import com.kakao.cafe.entity.ArticleEntity.ArticleEntityBuilder;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-01-21T17:17:49+0900",
    comments = "version: 1.4.1.Final, compiler: javac, environment: Java 11.0.11 (AdoptOpenJDK)"
)
@Component
public class ArticleMapperImpl implements ArticleMapper {

    @Override
    public ArticleEntity toArticleEntity(ArticleDto articleDto) {
        if ( articleDto == null ) {
            return null;
        }

        ArticleEntityBuilder articleEntity = ArticleEntity.builder();

        articleEntity.views( articleDto.getViews() );
        articleEntity.id( articleDto.getId() );
        articleEntity.title( articleDto.getTitle() );
        articleEntity.content( articleDto.getContent() );
        articleEntity.writer( articleDto.getWriter() );
        articleEntity.userId( articleDto.getUserId() );

        articleEntity.writeDate( java.time.LocalDateTime.now() );

        return articleEntity.build();
    }

    @Override
    public ArticleDto toArticleDto(ArticleEntity articleEntity) {
        if ( articleEntity == null ) {
            return null;
        }

        ArticleDtoBuilder articleDto = ArticleDto.builder();

        if ( articleEntity.getWriteDate() != null ) {
            articleDto.writeDate( DateTimeFormatter.ofPattern( "yyyy-MM-dd" ).format( articleEntity.getWriteDate() ) );
        }
        articleDto.id( articleEntity.getId() );
        articleDto.title( articleEntity.getTitle() );
        articleDto.content( articleEntity.getContent() );
        articleDto.writer( articleEntity.getWriter() );
        articleDto.views( articleEntity.getViews() );
        articleDto.userId( articleEntity.getUserId() );

        return articleDto.build();
    }

    @Override
    public List<ArticleDto> toArticleDtoList(List<ArticleEntity> articleEntityList) {
        if ( articleEntityList == null ) {
            return null;
        }

        List<ArticleDto> list = new ArrayList<ArticleDto>( articleEntityList.size() );
        for ( ArticleEntity articleEntity : articleEntityList ) {
            list.add( toArticleDto( articleEntity ) );
        }

        return list;
    }
}
