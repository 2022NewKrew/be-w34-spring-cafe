DROP TABLE IF EXISTS `comment`;
DROP TABLE IF EXISTS `post`;
DROP TABLE IF EXISTS `user`;

CREATE TABLE IF NOT EXISTS `user`
(
    `id`        BIGINT      NOT NULL AUTO_INCREMENT,
    `created`   TIMESTAMP   NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `email`     VARCHAR(30) NOT NULL,
    `password`  VARCHAR(80) NOT NULL,
    `name`      VARCHAR(10) NOT NULL,
    `isDeleted` BOOLEAN     NOT NULL default false,
    CONSTRAINT `USER_PK` PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `post`
(
    `id`         BIGINT      NOT NULL AUTO_INCREMENT,
    `created`    TIMESTAMP   NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `title`      VARCHAR(50) NOT NULL,
    `contents`   TEXT        NOT NULL,
    `user_id`    BIGINT      NOT NULL,
    `view_count` BIGINT      NOT NULL DEFAULT 0,
    `isDeleted`  BOOLEAN     NOT NULL default false,
    CONSTRAINT `POST_PK` PRIMARY KEY (`id`),
    CONSTRAINT `POST_USER` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
);

CREATE TABLE IF NOT EXISTS `comment`
(
    `id`        BIGINT    NOT NULL AUTO_INCREMENT,
    `created`   TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `post_id`   BIGINT    NOT NULL,
    `user_id`   BIGINT    NOT NULL,
    `contents`  TEXT      NOT NULL,
    `isDeleted` BOOLEAN   NOT NULL default false,
    CONSTRAINT `COMMENT_PK` PRIMARY KEY (`id`),
    CONSTRAINT `COMMENT_USER` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
    CONSTRAINT `COMMENT_POST` FOREIGN KEY (`post_id`) REFERENCES `post` (`id`)
);
