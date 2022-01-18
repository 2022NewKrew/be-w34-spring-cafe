package com.kakao.cafe.domain;

import com.kakao.cafe.exception.EmailFormatException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

@Getter
public class Email {

  private final String regex = "^(.+)@(.+)$";
  private String fullPath;

  private Email(String fullPath) {

    if(StringUtils.isBlank(fullPath) || isNotEmail(fullPath)) {
      throw new EmailFormatException();
    }

    this.fullPath = fullPath;
  }


  public static Email of(String fullPath) {
    return new Email(fullPath);
  }


  private boolean isNotEmail(String path) {
    Pattern pattern = Pattern.compile(regex);
    Matcher matcher = pattern.matcher(path);
    return !matcher.matches();
  }


  @Override
  public String toString() {
    return fullPath;
  }

}
