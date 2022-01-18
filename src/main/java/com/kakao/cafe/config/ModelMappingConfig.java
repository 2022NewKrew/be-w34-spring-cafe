package com.kakao.cafe.config;

import com.kakao.cafe.user.domain.entity.User;
import com.kakao.cafe.user.domain.entity.UserInfo;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class ModelMappingConfig {
    @Bean
    public ModelMapper modelMapper(List<Converter<?,?>> converters){
        ModelMapper modelMapper = new ModelMapper();
        for(Converter<?,?> converter : converters){
            modelMapper.addConverter(converter);
        }

        return modelMapper;
    }
}
