CREATE TABLE USER
(
    id INT NOT NULL,
    accid VARCHAR(20) NOT NULL ,
    accpw VARCHAR(20) NOT NULL,
    name VARCHAR(20) NOT NULL,
    email VARCHAR(50),
    PRIMARY KEY (id),
    UNIQUE (name)
);
CREATE TABLE ARTICLE
(
    id       INT         NOT NULL,
    writer   VARCHAR(20) NOT NULL,
    title    VARCHAR(50) NOT NULL,
    contents VARCHAR(999999),
    createddate VARCHAR(20) ,
    createdtime VARCHAR(20) ,
    PRIMARY KEY (id),
    FOREIGN KEY (writer) REFERENCES USER(name)
);