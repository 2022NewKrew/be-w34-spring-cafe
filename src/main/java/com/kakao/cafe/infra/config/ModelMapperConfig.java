package com.kakao.cafe.infra.config;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.kakao.cafe.module.model.dto.ArticleDtos.*;
import static com.kakao.cafe.module.model.dto.UserDtos.*;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration()
                .setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PRIVATE)
                .setFieldMatchingEnabled(true)
                .setSkipNullEnabled(true)
                .setMatchingStrategy(MatchingStrategies.STRICT);

        modelMapper.typeMap(UserDto.class, ArticlePostDto.class).addMappings(mapper -> {
            mapper.map(UserDto::getName, ArticlePostDto::setAuthor);
            mapper.map(UserDto::getId, ArticlePostDto::setAuthorId);
        });
        return modelMapper;
    }
}
