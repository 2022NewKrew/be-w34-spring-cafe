package com.kakao.cafe.dto;

import com.kakao.cafe.entity.Entity;

public interface Dto<T extends Entity<? extends Dto<T>>> {

    T toEntity();
}
