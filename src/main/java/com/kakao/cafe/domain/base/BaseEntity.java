package com.kakao.cafe.domain.base;

import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter @Setter
@EqualsAndHashCode
public class BaseEntity {
    private Long id;
    private LocalDateTime createdAt;
    private Boolean isDeleted;
}
