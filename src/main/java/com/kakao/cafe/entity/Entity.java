package com.kakao.cafe.entity;

import com.kakao.cafe.dto.Dto;

public interface Entity<T extends Dto<? extends Entity<T>>> {

    T toDto();
}
