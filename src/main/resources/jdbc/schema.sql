DROP TABLE IF EXISTS users;
CREATE TABLE users (
    id              BIGINT AUTO_INCREMENT PRIMARY KEY,
    email           VARCHAR(100) NOT NULL UNIQUE,
    nick_name       VARCHAR (100) NOT NULL,
    summary         VARCHAR (255) NOT NULL,
    profile         VARCHAR (255) NOT NULL,
    password        VARCHAR (100) NOT NULL,
    create_at       TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    modified_at     TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    last_login_at   TIMESTAMP NULL
);

DROP TABLE IF EXISTS article;
CREATE TABLE article (
    id              BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id         BIGINT NOT NULL,
    title           VARCHAR(255) NOT NULL,
    content         TEXT NOT NULL,
    read_count      BIGINT NOT NULL DEFAULT 0,
    create_at       TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    modified_at     TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(id)
);

ALTER TABLE article ADD is_deleted enum('NOT_DELETED', 'SOFT_DELETED', 'ADMIN_ONLY', 'COMPLETELY_DELETED') NOT NULL DEFAULT 'NOT_DELETED' AFTER read_count;

DROP TABLE IF EXISTS comment;
CREATE TABLE comment (
    id              BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id         BIGINT NOT NULL,
    article_id      BIGINT NOT NULL,
    content         TEXT NOT NULL,
    like_count      BIGINT NOT NULL DEFAULT 0,
    is_deleted      ENUM('NOT_DELETED', 'SOFT_DELETED', 'ADMIN_ONLY', 'COMPLETELY_DELETED') NOT NULL DEFAULT 'NOT_DELETED',
    create_at       TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ,
    modified_at     TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ,
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (article_id) REFERENCES article(id)
);

ALTER TABLE comment DROP like_count;

DROP TABLE IF EXISTS comment_like;
CREATE TABLE comment_like (
    prefix_id       VARCHAR(50) PRIMARY KEY,
    comment_id      BIGINT NOT NULL,
    user_id         BIGINT NOT NULL,
    FOREIGN KEY (comment_id) REFERENCES comment(id),
    FOREIGN KEY (user_id) REFERENCES users(id)
);