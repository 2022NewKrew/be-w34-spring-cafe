package com.kakao.cafe.application.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PaginationDto {
    private long totalCount;
    private long totalPageCount;
    private boolean startRange;
    private boolean endRange;
    private long startPage;
    private long endPage;
    private long currentPage;
}
