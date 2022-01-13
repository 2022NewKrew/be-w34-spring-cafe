create table member(
       id bigint NOT NULL AUTO_INCREMENT,
       user_id varchar(255),
       password varchar(255),
       name varchar(255),
       email varchar(255),
       role varchar(255) DEFAULT 'USER',
       PRIMARY KEY(id)
);

create table question(
     id bigint NOT NULL AUTO_INCREMENT,
     member_id bigint,
     writer varchar(255),
     title varchar(255),
     contents varchar(255),
     create_time timestamp DEFAULT current_timestamp,
     PRIMARY KEY(id),
     FOREIGN KEY(member_id) REFERENCES member(id)
);
