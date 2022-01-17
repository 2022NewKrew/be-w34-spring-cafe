DROP TABLE IF EXISTS post;
DROP TABLE IF EXISTS users;

CREATE TABLE users
(
    user_id     VARCHAR     NOT NULL UNIQUE,
    password    VARCHAR     NOT NULL,
    name        VARCHAR,
    email       VARCHAR,
    PRIMARY KEY(user_id)
);

CREATE TABLE post
(
    id          int     NOT NULL AUTO_INCREMENT,
    title       VARCHAR,
    content     CLOB,
    created_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    user_id    VARCHAR,
    PRIMARY KEY(id),
    FOREIGN KEY(user_id) REFERENCES users (user_id)
--     유저가 없어서 테스트가 제대로 동작하지 않기에 잠시 주석처리했습니다
--     다음 작업때 유저 추가한 테스트로 수정하겠습니다
);
