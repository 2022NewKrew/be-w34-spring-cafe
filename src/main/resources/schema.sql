DROP TABLE IF EXISTS `USER`;
CREATE TABLE IF NOT EXISTS `USER`
(
    ID       VARCHAR(50),
    EMAIL    VARCHAR(50) NOT NULL,
    NICKNAME VARCHAR(32) NOT NULL,
    PASSWORD VARCHAR(32) NOT NULL,
    PRIMARY KEY (ID)
);


DROP TABLE IF EXISTS `ARTICLE`;
CREATE TABLE IF NOT EXISTS `ARTICLE`
(
    ID         BIGINT AUTO_INCREMENT,
    TITLE      VARCHAR(100)  NOT NULL,
    CONTENT    VARCHAR(1000) NOT NULL,
    CREATED_AT DATETIME DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (ID)
);
