package com.kakao.cafe.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

public enum Delete {

  NOT_DELETED,
  SOFT_DELETED,
  ADMIN_ONLY,
  COMPLETELY_DELETED;

  @Override
  public String toString() {
    return name();
  }

  //'Not Deleted', 'Soft Deleted', 'Admin Only', 'Completely Deleted'

}

