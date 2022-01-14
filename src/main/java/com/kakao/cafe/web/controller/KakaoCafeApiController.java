package com.kakao.cafe.web.controller;

import com.kakao.cafe.web.common.EnableSession;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//TODO: EnableSession 가 여기 선언되면 Pointcut 대상이 안되는 현상.. 해결할 것.

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@RestController
@EnableSession
@RequestMapping("/api")
public @interface KakaoCafeApiController {

}
