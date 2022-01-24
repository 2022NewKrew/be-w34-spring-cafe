DROP TABLE IF EXISTS `USER`;
DROP TABLE IF EXISTS `ARTICLE`;
DROP TABLE IF EXISTS `REPLY`;

CREATE TABLE IF NOT EXISTS `USER`
(
    id        BIGINT AUTO_INCREMENT,
    email     VARCHAR(32) UNIQUE NOT NULL,
    nickname  VARCHAR(32) UNIQUE NOT NULL,
    password  VARCHAR(60)        NOT NULL,
    createdAt DATE,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS `ARTICLE`
(
    id        BIGINT AUTO_INCREMENT,
    userId    BIGINT      NOT NULL,
    title     VARCHAR(50) NOT NULL,
    body      TEXT        NOT NULL,
    createdAt DATETIME,
    views     INT DEFAULT 0,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS `REPLY`
(
    id        BIGINT AUTO_INCREMENT,
    userId    BIGINT       NOT NULL,
    articleId BIGINT       NOT NULL,
    comments  VARCHAR(255) NOT NULL,
    createdAt DATETIME,
    PRIMARY KEY (id)
);
