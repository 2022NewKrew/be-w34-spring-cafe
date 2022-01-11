package com.kakao.cafe.exception;

import java.util.Arrays;
import java.util.EnumSet;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;

public enum ErrorConst {

  UNKNOWN("알 수 없는 오류가 발생했습니다. 다시 시도해주세요.", "LC_000", HttpStatus.INTERNAL_SERVER_ERROR, Exception.class),
  DB_KEY_DUPLICATED("해당 데이터가 이미 있습니다.", "LC_001", HttpStatus.BAD_REQUEST, DuplicateKeyException.class),
  NO_EMAIL_FOUND("해당 이메일 데이터가 없습니다.", "LC_002", HttpStatus.BAD_REQUEST, NoEmailFoundException.class),
  INVALID_AUTHENTICATION("로그인 정보가 잘못되었습니다. EMAIL 또는 PASSWORD 를 다시 확인하세요.", "LC_003", HttpStatus.BAD_REQUEST, InvalidAuthenticationException.class),
  NO_MATCHING_UPDATE("갱신할 데이터가 없습니다.", "LC_004", HttpStatus.BAD_REQUEST, NoMatchingForUpdateException.class),
  NO_REQUIRED_VALUE("필수 값이 부족합니다. 다시 한번 확인해주세요.", "LC_005", HttpStatus.BAD_REQUEST, NoRequiredValueException.class),
  NO_ARTICLE_ID("해당 게시물을 찾을 수 없습니다.", "LC_006", HttpStatus.BAD_REQUEST, NoArticleException.class);
  ;

  private final String message;
  private final String code;
  private final HttpStatus status;
  private Class<? extends Throwable> exception;

  ErrorConst(String message, String code, HttpStatus status, Class<? extends Throwable> exception) {
    this.message = message;
    this.code = code;
    this.status = status;
    this.exception = exception;
  }

  public static ErrorConst findBy(Exception exception) {
    String className = exception.getClass().getName();

    // for debug
    LoggerFactory.getLogger(ErrorConst.class).debug("\n className : {}", className);

    return Arrays.stream(ErrorConst.values())
        .filter(errorConst -> errorConst.exception.getName().equals(className))
        .findAny()
        .orElse(ErrorConst.UNKNOWN);
  }

  public String getMessage() {
    return message;
  }

  public String getCode() {
    return code;
  }

  public HttpStatus getStatus() {
    return status;
  }

}
