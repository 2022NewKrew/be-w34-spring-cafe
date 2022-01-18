INSERT INTO
    users(email, nickname, password, createdAt)
VALUES
    ('test1@test.com', '테스터1', '1234', '20210101 13:30:50' ),
    ('test2@test.com', '테스터2', '1234', '20210102 14:30:50' ),
    ('test3@test.com', '테스터3', '1234', '20210103 15:30:50' );

INSERT INTO
    posts(writer_id, title, content, createdAt)
VALUES
    (1, '게시글 제목1', '게시글 내용', '20210111 13:30:50' ),
    (2, '게시글 제목22', '1234', '20210112 14:30:50' ),
    (1, '게시글 제목입니다.333', '1234

1234', '20210113 15:30:50' );
