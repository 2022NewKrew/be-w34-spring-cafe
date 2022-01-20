DROP TABLE IF EXISTS `REPLY`;
DROP TABLE IF EXISTS "ARTICLE";
DROP TABLE IF EXISTS `USER`;

CREATE TABLE IF NOT EXISTS `USER`
(
    ID
              INT
        PRIMARY
            KEY
        AUTO_INCREMENT,
    USERID
              VARCHAR(32),
    PASSWORD  VARCHAR(60),
    NAME      VARCHAR(32),
    EMAIL     VARCHAR(50),
    JOINED_AT DATE
);

CREATE TABLE IF NOT EXISTS "ARTICLE"
(
    ID
               INT
        PRIMARY
            KEY
        AUTO_INCREMENT,
    AUTHOR_ID
               INT,
    TITLE
               VARCHAR(50),
    CONTENT    VARCHAR(300),
    VIEWS      INT,
    CREATED_AT DATE,
    DELETED    BOOL DEFAULT FALSE,
    FOREIGN KEY
        (
         AUTHOR_ID
            )
        REFERENCES `USER`
            (
             ID
                )
);

CREATE TABLE IF NOT EXISTS "REPLY"
(
    id         INT PRIMARY KEY AUTO_INCREMENT,
    author_id  INT,
    article_id INT,
    content    VARCHAR(200),
    created_at DATE,
    deleted    BOOL DEFAULT FALSE,
    FOREIGN KEY (author_id) references `USER` (id),
    FOREIGN KEY (article_id) references `ARTICLE` (id)
)

