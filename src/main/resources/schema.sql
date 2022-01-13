CREATE TABLE IF NOT EXISTS "USER"
(
    ID INT AUTO_INCREMENT,
    STRING_ID VARCHAR(64),
    NAME VARCHAR(64),
    PASSWORD VARCHAR(64),
    PRIMARY KEY(ID),
    UNIQUE(STRING_ID)
);

CREATE TABLE IF NOT EXISTS "QUESTION"
(
    ID INT AUTO_INCREMENT,
    TITLE VARCHAR(64),
    WRITER VARCHAR(64),
    CONTENTS TEXT,
    CREATED_AT TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY(ID)
);
