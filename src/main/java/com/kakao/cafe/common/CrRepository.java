package com.kakao.cafe.common;

import java.time.OffsetDateTime;
import java.util.List;

public interface CrRepository<T extends BaseEntity> {
    long save(T target);

    T fetch(long id);

    List<T> fetchAll();

    default void beforeSave(T target) {
        OffsetDateTime now = OffsetDateTime.now();
        target.setCreatedAt(now);
        target.setUpdatedAt(now);
    }
}
