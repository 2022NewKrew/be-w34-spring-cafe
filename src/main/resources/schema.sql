DROP TABLE IF EXISTS `MEMBER`;

CREATE TABLE `MEMBER`
(
    id     BIGINT PRIMARY KEY AUTO_INCREMENT,
    USERID VARCHAR(64),
    PASSWORD VARCHAR(64),
    EMAIL VARCHAR(64)
);

DROP TABLE IF EXISTS `ARTICLE`;
CREATE TABLE `ARTICLE`
(
    id    BIGINT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(255),
    content TEXT,
);
