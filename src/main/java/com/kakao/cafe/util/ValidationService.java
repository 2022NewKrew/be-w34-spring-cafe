package com.kakao.cafe.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

public class ValidationService {
    private static final Logger logger;
    private static final Validator validator;

    static  {
        logger = LoggerFactory.getLogger(ValidationService.class);

        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    public static <T> void validate(T object){
        Set<ConstraintViolation<T>> constraintViolations = validator.validate(object);
        if(constraintViolations.isEmpty()){
            return;
        }

        logger.info("Validation이 실패하였습니다.");
        constraintViolations.stream()
                .map(violation -> String.format("[%s]: %s", violation.getPropertyPath(), violation.getMessage()))
                .forEach(logger::info);

        throw new IllegalArgumentException("검증에 실패하였습니다.");
    }
}
