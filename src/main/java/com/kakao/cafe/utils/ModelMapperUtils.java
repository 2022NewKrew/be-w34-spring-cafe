package com.kakao.cafe.utils;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

public class ModelMapperUtils {

    private static ModelMapper modelMapper = new ModelMapper();
    private static ModelMapper strictModelMapper = new ModelMapper();

    public static ModelMapper getModelMapper() {
        return modelMapper;
    }

    public static ModelMapper getStrictModelMapper() {
        strictModelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT).setSkipNullEnabled(true);
        return strictModelMapper;
    }

}
