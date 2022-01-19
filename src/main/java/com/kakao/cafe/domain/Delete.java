package com.kakao.cafe.domain;

/**
 * DB 삭제 칼럼 - is_deleted 와 매핑하여 사용.
 */
public enum Delete {

  NULL,
  NOT_DELETED,
  SOFT_DELETED,
  ADMIN_ONLY,
  COMPLETELY_DELETED;


  /**
   * auto-generated by intellij
   *
   * @return string for log
   */
  @Override
  public String toString() {
    return name();
  }

}

