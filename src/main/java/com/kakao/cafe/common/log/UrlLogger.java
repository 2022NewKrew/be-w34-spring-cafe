package com.kakao.cafe.common.log;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.UUID;

@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
@Component
@Slf4j
public class UrlLogger {

    private String uuid;
    private String requestURL;

    public void setRequestURL(String requestURL) {
        this.requestURL = requestURL;
    }

    public void log(String message) {
        log.debug("uuid : {} requestURL : {} message : {}", uuid, requestURL, message);
    }

    @PostConstruct
    private void init() {
        uuid = UUID.randomUUID().toString();
        log.debug("request 요청에 의한 식별자가 생성되었습니다. uuid = {}", uuid);
    }

    @PreDestroy
    private void close() {
        log.debug("request 요청을 종료합니다. uuid = {}", uuid);
    }
}
