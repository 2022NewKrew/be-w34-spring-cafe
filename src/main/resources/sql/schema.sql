-- 게시판 데이터베이스 스키마 예시
-- http://blog.sodhanalibrary.com/2015/11/simple-reddit-database-design.html#.YeA-Zn1Bz0o

CREATE TABLE IF NOT EXISTS cafe_user
(
    id               BIGINT AUTO_INCREMENT,
    username         VARCHAR(255) NOT NULL UNIQUE,
    password         VARCHAR(255) NOT NULL,
    email            VARCHAR(255) NOT NULL UNIQUE,
    display_name     VARCHAR(255) NOT NULL,
    status           VARCHAR(255) NOT NULL,
    created_at       TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    last_modified_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

    PRIMARY KEY (id)
);


CREATE TABLE IF NOT EXISTS thread
(
    id               BIGINT AUTO_INCREMENT,
    parent_id        BIGINT,
    author_id        BIGINT        NOT NULL,
    title            VARCHAR(255)  NOT NULL,
    content          VARCHAR(3071) NOT NULL,
    status           VARCHAR(255)  NOT NULL,
    type             VARCHAR(255)  NOT NULL,
    created_at       TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    last_modified_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

    PRIMARY KEY (id),
    FOREIGN KEY (parent_id) REFERENCES thread (id),
    FOREIGN KEY (author_id) REFERENCES cafe_user (id)
);
