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
    content         CLOB NOT NULL,
    read_count      BIGINT NOT NULL DEFAULT 0,
    create_at       TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    modified_at     TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(id)
);

ALTER TABLE article ADD is_deleted enum('Not Deleted', 'Soft Deleted', 'Admin Only', 'Completely Deleted') NOT NULL DEFAULT 'Not Deleted' AFTER read_count;