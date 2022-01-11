DROP TABLE IF EXISTS Users;
DROP TABLE IF EXISTS POSTS;

CREATE TABLE IF NOT EXISTS Users
(
    ID varchar
(
    255
) NOT NULL PRIMARY KEY,
    PASSWORD varchar
(
    255
),
    EMAIL varchar
(
    255
),
    NAME varchar
(
    255
)
    );

CREATE TABLE IF NOT EXISTS POSTS
(
    ID
    BIGINT
    AUTO_INCREMENT
    PRIMARY
    KEY,
    TITLE
    VARCHAR
(
    255
),
    WRITER VARCHAR
(
    255
),
    CONTENTS VARCHAR
(
    255
)
    );
