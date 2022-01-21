package com.kakao.cafe.exception;

import java.util.Arrays;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;

public enum ErrorConst {

  UNKNOWN("알 수 없는 오류가 발생했습니다. 다시 시도해주세요.", "LC_000", HttpStatus.INTERNAL_SERVER_ERROR, Exception.class),
  DB_KEY_DUPLICATED("해당 데이터가 이미 있습니다.", "LC_001", HttpStatus.BAD_REQUEST, DuplicateKeyException.class),
  NO_EMAIL_FOUND("해당 이메일 데이터가 없습니다.", "LC_002", HttpStatus.BAD_REQUEST, NoEmailFoundException.class),
  INVALID_AUTHENTICATION("로그인 정보가 잘못되었습니다. EMAIL 또는 PASSWORD 를 다시 확인하세요.", "LC_003", HttpStatus.UNAUTHORIZED, InvalidAuthenticationException.class),
  NO_MATCHING_UPDATE("갱신할 데이터가 없습니다.", "LC_004", HttpStatus.BAD_REQUEST, NoMatchingForUpdateException.class),
  NO_REQUIRED_VALUE("필수 값이 부족합니다. 다시 한번 확인해주세요.", "LC_005", HttpStatus.BAD_REQUEST, NoRequiredValueException.class),
  NO_ARTICLE_ID("해당 게시물을 찾을 수 없습니다.", "LC_006", HttpStatus.BAD_REQUEST, NoArticleException.class),
  NEED_AUTHORITY("권한이 없습니다. 다시 한번 확인해주세요.", "LC_007", HttpStatus.FORBIDDEN, NoAuthorityException.class),
  WRONG_PASSWORD("패스워드가 일치하지 않습니다. 다시 시도해주세요.", "LC_008", HttpStatus.BAD_REQUEST, WrongPasswordException.class),
  WRONG_EMAIL_FORMAT("이메일 양식이 일치하지 않습니다.", "LC_009", HttpStatus.BAD_REQUEST, EmailFormatException.class),
  DB_DATA_VIOLATION("요청이 잘못되었습니다. (제약조건 위반) 다시 한번 확인해주세요.", "LC_010", HttpStatus.BAD_REQUEST, DataIntegrityViolationException.class),
  NO_COMMENT_ID("해당 댓글ID 를 찾을 수 없습니다.", "LC011", HttpStatus.BAD_REQUEST, NoCommentException.class),
  CANNOT_DELETE("삭제할 수 없습니다.", "LC012", HttpStatus.BAD_REQUEST, CannotDeleteException.class)
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
