package com.kakao.cafe.util;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Page<EN> {
    private List<EN> entityList;
    private int totalPage;
    private int totalCount;
    Pageable pageable;
}
