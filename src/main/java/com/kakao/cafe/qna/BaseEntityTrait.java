package com.kakao.cafe.qna;

/**
 * Created by melodist
 * Date: 2022-02-04 004
 * Time: 오후 6:21
 */
public interface BaseEntityTrait {
    BaseEntity getBaseEntity();

    default void setId(Integer id) {
        getBaseEntity().setId(id);
    }

    default void updateContents(String contents) {
        getBaseEntity().updateContents(contents);
    }

    default void deleteEntity() {
        getBaseEntity().deleteEntity();
    }
}
