package com.kakao.cafe.dto;

import com.kakao.cafe.util.Pageable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@AllArgsConstructor
@Data
public class PageRequestDto {
    private int page;
    private int size;

    public PageRequestDto() {
        this.page = 1;
        this.size = 10;
    }

    public Pageable getPageable() {
        return Pageable.builder()
                .page(this.page - 1)
                .size(this.size)
                .build();
    }
}
