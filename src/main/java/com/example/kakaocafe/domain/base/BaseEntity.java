package com.example.kakaocafe.domain.base;

import java.time.LocalDateTime;
import java.util.Objects;

public class BaseEntity {
    protected Long id;
    protected LocalDateTime created;

    public BaseEntity(Long id, LocalDateTime created) {
        this.id = id;
        this.created = created;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BaseEntity that = (BaseEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public Long getId() {
        return id;
    }
}
