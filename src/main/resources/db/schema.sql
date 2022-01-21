SET foreign_key_checks = 0;
DROP TABLE IF EXISTS member;

create table member(
       id bigint NOT NULL AUTO_INCREMENT,
       user_id varchar(255),
       password varchar(255),
       name varchar(255),
       email varchar(255),
       role varchar(255) DEFAULT 'USER',
       PRIMARY KEY(id)
);

DROP TABLE IF EXISTS question;

create table question(
     id bigint NOT NULL AUTO_INCREMENT,
     member_id bigint,
     writer varchar(255),
     title varchar(255),
     contents varchar(255),
     create_time timestamp DEFAULT current_timestamp,
     status varchar (255) DEFAULT 'NEW',
     PRIMARY KEY(id),
     FOREIGN KEY(member_id) REFERENCES member(id)
);

DROP TABLE IF EXISTS reply;
create table reply(
    id bigint NOT NULL AUTO_INCREMENT,
    member_id bigint,
    question_id bigint,
    writer varchar(255),
    comment varchar(255),
    create_time timestamp DEFAULT current_timestamp,
    status varchar (255) DEFAULT 'NEW',
    PRIMARY KEY(id),
    FOREIGN KEY(question_id) REFERENCES question(id)
);

SET foreign_key_checks = 1;


-- data

insert into member(user_id, password, name, email)
values ('jm.hong', 'jm.hong', 'Hongjeongmin', 'jm.hong@kakaocorp.com');
insert into member(user_id, password, name, email)
values ('finn.kakao', 'finn.kakao', 'Kimkakao', 'finn.kakao@kakaocorp.com');
insert into member(user_id, password, name, email, role)
values ('admin', 'admin', '운영자', 'admin@kakaocorp.com', 'ADMIN');

insert into question(member_id, writer, title, contents)
values (1, 'jm.hong', '안녕하세요 반갑습니다.', '오늘 날씨가 좋네요. 정말 날시가 좋은것 같아요!');
insert into question(member_id, writer, title, contents)
values (2, 'finn.kakao', '오늘 점심 먹었습니다..', '점심밥으로는 초밥이 제일 무난한것 같아요!');
insert into question(member_id, writer, title, contents)
values (1, 'jm.hong', '안녕하세요 반갑습니다.', '오늘 날씨가 좋네요. 정말 날시가 좋은것 같아요!');
insert into question(member_id, writer, title, contents)
values (2, 'finn.kakao', '오늘 점심 먹었습니다..', '점심밥으로는 초밥이 제일 무난한것 같아요!');
insert into question(member_id, writer, title, contents)
values (1, 'jm.hong', '안녕하세요 반갑습니다.', '오늘 날씨가 좋네요. 정말 날시가 좋은것 같아요!');
insert into question(member_id, writer, title, contents)
values (2, 'finn.kakao', '오늘 점심 먹었습니다..', '점심밥으로는 초밥이 제일 무난한것 같아요!');
insert into question(member_id, writer, title, contents)
values (1, 'jm.hong', '안녕하세요 반갑습니다.', '오늘 날씨가 좋네요. 정말 날시가 좋은것 같아요!');
insert into question(member_id, writer, title, contents)
values (2, 'finn.kakao', '오늘 점심 먹었습니다..', '점심밥으로는 초밥이 제일 무난한것 같아요!');
insert into question(member_id, writer, title, contents)
values (1, 'jm.hong', '안녕하세요 반갑습니다.', '오늘 날씨가 좋네요. 정말 날시가 좋은것 같아요!');
insert into question(member_id, writer, title, contents)
values (2, 'finn.kakao', '오늘 점심 먹었습니다..', '점심밥으로는 초밥이 제일 무난한것 같아요!');
insert into question(member_id, writer, title, contents)
values (1, 'jm.hong', '안녕하세요 반갑습니다.', '오늘 날씨가 좋네요. 정말 날시가 좋은것 같아요!');
insert into question(member_id, writer, title, contents)
values (2, 'finn.kakao', '오늘 점심 먹었습니다..', '점심밥으로는 초밥이 제일 무난한것 같아요!');
insert into question(member_id, writer, title, contents)
values (1, 'jm.hong', '안녕하세요 반갑습니다.', '오늘 날씨가 좋네요. 정말 날시가 좋은것 같아요!');
insert into question(member_id, writer, title, contents)
values (2, 'finn.kakao', '오늘 점심 먹었습니다..', '점심밥으로는 초밥이 제일 무난한것 같아요!');
insert into question(member_id, writer, title, contents)
values (1, 'jm.hong', '안녕하세요 반갑습니다.', '오늘 날씨가 좋네요. 정말 날시가 좋은것 같아요!');
insert into question(member_id, writer, title, contents)
values (2, 'finn.kakao', '오늘 점심 먹었습니다..', '점심밥으로는 초밥이 제일 무난한것 같아요!');
insert into question(member_id, writer, title, contents)
values (1, 'jm.hong', '안녕하세요 반갑습니다.', '오늘 날씨가 좋네요. 정말 날시가 좋은것 같아요!');
insert into question(member_id, writer, title, contents)
values (2, 'finn.kakao', '오늘 점심 먹었습니다..', '점심밥으로는 초밥이 제일 무난한것 같아요!');
insert into question(member_id, writer, title, contents)
values (1, 'jm.hong', '안녕하세요 반갑습니다.', '오늘 날씨가 좋네요. 정말 날시가 좋은것 같아요!');
insert into question(member_id, writer, title, contents)
values (2, 'finn.kakao', '오늘 점심 먹었습니다..', '점심밥으로는 초밥이 제일 무난한것 같아요!');
insert into question(member_id, writer, title, contents)
values (1, 'jm.hong', '안녕하세요 반갑습니다.', '오늘 날씨가 좋네요. 정말 날시가 좋은것 같아요!');
insert into question(member_id, writer, title, contents)
values (2, 'finn.kakao', '오늘 점심 먹었습니다..', '점심밥으로는 초밥이 제일 무난한것 같아요!');
insert into question(member_id, writer, title, contents)
values (1, 'jm.hong', '안녕하세요 반갑습니다.', '오늘 날씨가 좋네요. 정말 날시가 좋은것 같아요!');
insert into question(member_id, writer, title, contents)
values (2, 'finn.kakao', '오늘 점심 먹었습니다..', '점심밥으로는 초밥이 제일 무난한것 같아요!');
insert into question(member_id, writer, title, contents)
values (1, 'jm.hong', '안녕하세요 반갑습니다.', '오늘 날씨가 좋네요. 정말 날시가 좋은것 같아요!');
insert into question(member_id, writer, title, contents)
values (2, 'finn.kakao', '오늘 점심 먹었습니다..', '점심밥으로는 초밥이 제일 무난한것 같아요!');
insert into question(member_id, writer, title, contents)
values (1, 'jm.hong', '안녕하세요 반갑습니다.', '오늘 날씨가 좋네요. 정말 날시가 좋은것 같아요!');
insert into question(member_id, writer, title, contents)
values (2, 'finn.kakao', '오늘 점심 먹었습니다..', '점심밥으로는 초밥이 제일 무난한것 같아요!');
insert into question(member_id, writer, title, contents)
values (1, 'jm.hong', '안녕하세요 반갑습니다.', '오늘 날씨가 좋네요. 정말 날시가 좋은것 같아요!');
insert into question(member_id, writer, title, contents)
values (2, 'finn.kakao', '오늘 점심 먹었습니다..', '점심밥으로는 초밥이 제일 무난한것 같아요!');
insert into question(member_id, writer, title, contents)
values (1, 'jm.hong', '안녕하세요 반갑습니다.', '오늘 날씨가 좋네요. 정말 날시가 좋은것 같아요!');
insert into question(member_id, writer, title, contents)
values (2, 'finn.kakao', '오늘 점심 먹었습니다..', '점심밥으로는 초밥이 제일 무난한것 같아요!');
insert into question(member_id, writer, title, contents)
values (1, 'jm.hong', '안녕하세요 반갑습니다.', '오늘 날씨가 좋네요. 정말 날시가 좋은것 같아요!');
insert into question(member_id, writer, title, contents)
values (2, 'finn.kakao', '오늘 점심 먹었습니다..', '점심밥으로는 초밥이 제일 무난한것 같아요!');
insert into question(member_id, writer, title, contents)
values (1, 'jm.hong', '안녕하세요 반갑습니다.', '오늘 날씨가 좋네요. 정말 날시가 좋은것 같아요!');
insert into question(member_id, writer, title, contents)
values (2, 'finn.kakao', '오늘 점심 먹었습니다..', '점심밥으로는 초밥이 제일 무난한것 같아요!');
insert into question(member_id, writer, title, contents)
values (1, 'jm.hong', '안녕하세요 반갑습니다.', '오늘 날씨가 좋네요. 정말 날시가 좋은것 같아요!');
insert into question(member_id, writer, title, contents)
values (2, 'finn.kakao', '오늘 점심 먹었습니다..', '점심밥으로는 초밥이 제일 무난한것 같아요!');
insert into question(member_id, writer, title, contents)
values (1, 'jm.hong', '안녕하세요 반갑습니다.', '오늘 날씨가 좋네요. 정말 날시가 좋은것 같아요!');
insert into question(member_id, writer, title, contents)
values (2, 'finn.kakao', '오늘 점심 먹었습니다..', '점심밥으로는 초밥이 제일 무난한것 같아요!');
insert into question(member_id, writer, title, contents)
values (1, 'jm.hong', '안녕하세요 반갑습니다.', '오늘 날씨가 좋네요. 정말 날시가 좋은것 같아요!');
insert into question(member_id, writer, title, contents)
values (2, 'finn.kakao', '오늘 점심 먹었습니다..', '점심밥으로는 초밥이 제일 무난한것 같아요!');
insert into question(member_id, writer, title, contents)
values (1, 'jm.hong', '안녕하세요 반갑습니다.', '오늘 날씨가 좋네요. 정말 날시가 좋은것 같아요!');
insert into question(member_id, writer, title, contents)
values (2, 'finn.kakao', '오늘 점심 먹었습니다..', '점심밥으로는 초밥이 제일 무난한것 같아요!');
insert into question(member_id, writer, title, contents)
values (1, 'jm.hong', '안녕하세요 반갑습니다.', '오늘 날씨가 좋네요. 정말 날시가 좋은것 같아요!');
insert into question(member_id, writer, title, contents)
values (2, 'finn.kakao', '오늘 점심 먹었습니다..', '점심밥으로는 초밥이 제일 무난한것 같아요!');
insert into question(member_id, writer, title, contents)
values (1, 'jm.hong', '안녕하세요 반갑습니다.', '오늘 날씨가 좋네요. 정말 날시가 좋은것 같아요!');
insert into question(member_id, writer, title, contents)
values (2, 'finn.kakao', '오늘 점심 먹었습니다..', '점심밥으로는 초밥이 제일 무난한것 같아요!');
insert into question(member_id, writer, title, contents)
values (1, 'jm.hong', '안녕하세요 반갑습니다.', '오늘 날씨가 좋네요. 정말 날시가 좋은것 같아요!');
insert into question(member_id, writer, title, contents)
values (2, 'finn.kakao', '오늘 점심 먹었습니다..', '점심밥으로는 초밥이 제일 무난한것 같아요!');
insert into question(member_id, writer, title, contents)
values (1, 'jm.hong', '안녕하세요 반갑습니다.', '오늘 날씨가 좋네요. 정말 날시가 좋은것 같아요!');
insert into question(member_id, writer, title, contents)
values (2, 'finn.kakao', '오늘 점심 먹었습니다..', '점심밥으로는 초밥이 제일 무난한것 같아요!');
insert into question(member_id, writer, title, contents)
values (1, 'jm.hong', '안녕하세요 반갑습니다.', '오늘 날씨가 좋네요. 정말 날시가 좋은것 같아요!');
insert into question(member_id, writer, title, contents)
values (2, 'finn.kakao', '오늘 점심 먹었습니다..', '점심밥으로는 초밥이 제일 무난한것 같아요!');
insert into question(member_id, writer, title, contents)
values (1, 'jm.hong', '안녕하세요 반갑습니다.', '오늘 날씨가 좋네요. 정말 날시가 좋은것 같아요!');
insert into question(member_id, writer, title, contents)
values (2, 'finn.kakao', '오늘 점심 먹었습니다..', '점심밥으로는 초밥이 제일 무난한것 같아요!');
insert into question(member_id, writer, title, contents)
values (1, 'jm.hong', '안녕하세요 반갑습니다.', '오늘 날씨가 좋네요. 정말 날시가 좋은것 같아요!');
insert into question(member_id, writer, title, contents)
values (2, 'finn.kakao', '오늘 점심 먹었습니다..', '점심밥으로는 초밥이 제일 무난한것 같아요!');
insert into question(member_id, writer, title, contents)
values (1, 'jm.hong', '안녕하세요 반갑습니다.', '오늘 날씨가 좋네요. 정말 날시가 좋은것 같아요!');
insert into question(member_id, writer, title, contents)
values (2, 'finn.kakao', '오늘 점심 먹었습니다..', '점심밥으로는 초밥이 제일 무난한것 같아요!');
insert into question(member_id, writer, title, contents)
values (1, 'jm.hong', '안녕하세요 반갑습니다.', '오늘 날씨가 좋네요. 정말 날시가 좋은것 같아요!');
insert into question(member_id, writer, title, contents)
values (2, 'finn.kakao', '오늘 점심 먹었습니다..', '점심밥으로는 초밥이 제일 무난한것 같아요!');
insert into question(member_id, writer, title, contents)
values (1, 'jm.hong', '안녕하세요 반갑습니다.', '오늘 날씨가 좋네요. 정말 날시가 좋은것 같아요!');
insert into question(member_id, writer, title, contents)
values (2, 'finn.kakao', '오늘 점심 먹었습니다..', '점심밥으로는 초밥이 제일 무난한것 같아요!');
insert into question(member_id, writer, title, contents)
values (1, 'jm.hong', '안녕하세요 반갑습니다.', '오늘 날씨가 좋네요. 정말 날시가 좋은것 같아요!');
insert into question(member_id, writer, title, contents)
values (2, 'finn.kakao', '오늘 점심 먹었습니다..', '점심밥으로는 초밥이 제일 무난한것 같아요!');
insert into question(member_id, writer, title, contents)
values (1, 'jm.hong', '안녕하세요 반갑습니다.', '오늘 날씨가 좋네요. 정말 날시가 좋은것 같아요!');
insert into question(member_id, writer, title, contents)
values (2, 'finn.kakao', '오늘 점심 먹었습니다..', '점심밥으로는 초밥이 제일 무난한것 같아요!');
insert into question(member_id, writer, title, contents)
values (1, 'jm.hong', '안녕하세요 반갑습니다.', '오늘 날씨가 좋네요. 정말 날시가 좋은것 같아요!');
insert into question(member_id, writer, title, contents)
values (2, 'finn.kakao', '오늘 점심 먹었습니다..', '점심밥으로는 초밥이 제일 무난한것 같아요!');
insert into question(member_id, writer, title, contents)
values (1, 'jm.hong', '안녕하세요 반갑습니다.', '오늘 날씨가 좋네요. 정말 날시가 좋은것 같아요!');
insert into question(member_id, writer, title, contents)
values (2, 'finn.kakao', '오늘 점심 먹었습니다..', '점심밥으로는 초밥이 제일 무난한것 같아요!');
insert into question(member_id, writer, title, contents)
values (1, 'jm.hong', '안녕하세요 반갑습니다.', '오늘 날씨가 좋네요. 정말 날시가 좋은것 같아요!');
insert into question(member_id, writer, title, contents)
values (2, 'finn.kakao', '오늘 점심 먹었습니다..', '점심밥으로는 초밥이 제일 무난한것 같아요!');
insert into question(member_id, writer, title, contents)
values (1, 'jm.hong', '안녕하세요 반갑습니다.', '오늘 날씨가 좋네요. 정말 날시가 좋은것 같아요!');
insert into question(member_id, writer, title, contents)
values (2, 'finn.kakao', '오늘 점심 먹었습니다..', '점심밥으로는 초밥이 제일 무난한것 같아요!');
insert into question(member_id, writer, title, contents)
values (1, 'jm.hong', '안녕하세요 반갑습니다.', '오늘 날씨가 좋네요. 정말 날시가 좋은것 같아요!');
insert into question(member_id, writer, title, contents)
values (2, 'finn.kakao', '오늘 점심 먹었습니다..', '점심밥으로는 초밥이 제일 무난한것 같아요!');
insert into question(member_id, writer, title, contents)
values (1, 'jm.hong', '안녕하세요 반갑습니다.', '오늘 날씨가 좋네요. 정말 날시가 좋은것 같아요!');
insert into question(member_id, writer, title, contents)
values (2, 'finn.kakao', '오늘 점심 먹었습니다..', '점심밥으로는 초밥이 제일 무난한것 같아요!');
insert into question(member_id, writer, title, contents)
values (1, 'jm.hong', '안녕하세요 반갑습니다.', '오늘 날씨가 좋네요. 정말 날시가 좋은것 같아요!');
insert into question(member_id, writer, title, contents)
values (2, 'finn.kakao', '오늘 점심 먹었습니다..', '점심밥으로는 초밥이 제일 무난한것 같아요!');
insert into question(member_id, writer, title, contents)
values (1, 'jm.hong', '안녕하세요 반갑습니다.', '오늘 날씨가 좋네요. 정말 날시가 좋은것 같아요!');
insert into question(member_id, writer, title, contents)
values (2, 'finn.kakao', '오늘 점심 먹었습니다..', '점심밥으로는 초밥이 제일 무난한것 같아요!');
insert into question(member_id, writer, title, contents)
values (1, 'jm.hong', '안녕하세요 반갑습니다.', '오늘 날씨가 좋네요. 정말 날시가 좋은것 같아요!');
insert into question(member_id, writer, title, contents)
values (2, 'finn.kakao', '오늘 점심 먹었습니다..', '점심밥으로는 초밥이 제일 무난한것 같아요!');
insert into question(member_id, writer, title, contents)
values (1, 'jm.hong', '안녕하세요 반갑습니다.', '오늘 날씨가 좋네요. 정말 날시가 좋은것 같아요!');
insert into question(member_id, writer, title, contents)
values (2, 'finn.kakao', '오늘 점심 먹었습니다..', '점심밥으로는 초밥이 제일 무난한것 같아요!');
insert into question(member_id, writer, title, contents)
values (1, 'jm.hong', '안녕하세요 반갑습니다.', '오늘 날씨가 좋네요. 정말 날시가 좋은것 같아요!');
insert into question(member_id, writer, title, contents)
values (2, 'finn.kakao', '오늘 점심 먹었습니다..', '점심밥으로는 초밥이 제일 무난한것 같아요!');
insert into question(member_id, writer, title, contents)
values (1, 'jm.hong', '안녕하세요 반갑습니다.', '오늘 날씨가 좋네요. 정말 날시가 좋은것 같아요!');
insert into question(member_id, writer, title, contents)
values (2, 'finn.kakao', '오늘 점심 먹었습니다..', '점심밥으로는 초밥이 제일 무난한것 같아요!');
insert into question(member_id, writer, title, contents)
values (1, 'jm.hong', '안녕하세요 반갑습니다.', '오늘 날씨가 좋네요. 정말 날시가 좋은것 같아요!');
insert into question(member_id, writer, title, contents)
values (2, 'finn.kakao', '오늘 점심 먹었습니다..', '점심밥으로는 초밥이 제일 무난한것 같아요!');
insert into question(member_id, writer, title, contents)
values (1, 'jm.hong', '안녕하세요 반갑습니다.', '오늘 날씨가 좋네요. 정말 날시가 좋은것 같아요!');
insert into question(member_id, writer, title, contents)
values (2, 'finn.kakao', '오늘 점심 먹었습니다..', '점심밥으로는 초밥이 제일 무난한것 같아요!');
insert into question(member_id, writer, title, contents)
values (1, 'jm.hong', '안녕하세요 반갑습니다.', '오늘 날씨가 좋네요. 정말 날시가 좋은것 같아요!');
insert into question(member_id, writer, title, contents)
values (2, 'finn.kakao', '오늘 점심 먹었습니다..', '점심밥으로는 초밥이 제일 무난한것 같아요!');
insert into question(member_id, writer, title, contents)
values (1, 'jm.hong', '안녕하세요 반갑습니다.', '오늘 날씨가 좋네요. 정말 날시가 좋은것 같아요!');
insert into question(member_id, writer, title, contents)
values (2, 'finn.kakao', '오늘 점심 먹었습니다..', '점심밥으로는 초밥이 제일 무난한것 같아요!');
insert into question(member_id, writer, title, contents)
values (1, 'jm.hong', '안녕하세요 반갑습니다.', '오늘 날씨가 좋네요. 정말 날시가 좋은것 같아요!');
insert into question(member_id, writer, title, contents)
values (2, 'finn.kakao', '오늘 점심 먹었습니다..', '점심밥으로는 초밥이 제일 무난한것 같아요!');
insert into question(member_id, writer, title, contents)
values (1, 'jm.hong', '안녕하세요 반갑습니다.', '오늘 날씨가 좋네요. 정말 날시가 좋은것 같아요!');
insert into question(member_id, writer, title, contents)
values (2, 'finn.kakao', '오늘 점심 먹었습니다..', '점심밥으로는 초밥이 제일 무난한것 같아요!');
insert into question(member_id, writer, title, contents)
values (1, 'jm.hong', '안녕하세요 반갑습니다.', '오늘 날씨가 좋네요. 정말 날시가 좋은것 같아요!');
insert into question(member_id, writer, title, contents)
values (2, 'finn.kakao', '오늘 점심 먹었습니다..', '점심밥으로는 초밥이 제일 무난한것 같아요!');
insert into question(member_id, writer, title, contents)
values (1, 'jm.hong', '안녕하세요 반갑습니다.', '오늘 날씨가 좋네요. 정말 날시가 좋은것 같아요!');
insert into question(member_id, writer, title, contents)
values (2, 'finn.kakao', '오늘 점심 먹었습니다..', '점심밥으로는 초밥이 제일 무난한것 같아요!');
insert into question(member_id, writer, title, contents)
values (1, 'jm.hong', '안녕하세요 반갑습니다.', '오늘 날씨가 좋네요. 정말 날시가 좋은것 같아요!');
insert into question(member_id, writer, title, contents)
values (2, 'finn.kakao', '오늘 점심 먹었습니다..', '점심밥으로는 초밥이 제일 무난한것 같아요!');
insert into question(member_id, writer, title, contents)
values (1, 'jm.hong', '안녕하세요 반갑습니다.', '오늘 날씨가 좋네요. 정말 날시가 좋은것 같아요!');
insert into question(member_id, writer, title, contents)
values (2, 'finn.kakao', '오늘 점심 먹었습니다..', '점심밥으로는 초밥이 제일 무난한것 같아요!');
insert into question(member_id, writer, title, contents)
values (1, 'jm.hong', '안녕하세요 반갑습니다.', '오늘 날씨가 좋네요. 정말 날시가 좋은것 같아요!');
insert into question(member_id, writer, title, contents)
values (2, 'finn.kakao', '오늘 점심 먹었습니다..', '점심밥으로는 초밥이 제일 무난한것 같아요!');
insert into question(member_id, writer, title, contents)
values (1, 'jm.hong', '안녕하세요 반갑습니다.', '오늘 날씨가 좋네요. 정말 날시가 좋은것 같아요!');
insert into question(member_id, writer, title, contents)
values (2, 'finn.kakao', '오늘 점심 먹었습니다..', '점심밥으로는 초밥이 제일 무난한것 같아요!');
insert into question(member_id, writer, title, contents)
values (1, 'jm.hong', '안녕하세요 반갑습니다.', '오늘 날씨가 좋네요. 정말 날시가 좋은것 같아요!');
insert into question(member_id, writer, title, contents)
values (2, 'finn.kakao', '오늘 점심 먹었습니다..', '점심밥으로는 초밥이 제일 무난한것 같아요!');
insert into question(member_id, writer, title, contents)
values (1, 'jm.hong', '안녕하세요 반갑습니다.', '오늘 날씨가 좋네요. 정말 날시가 좋은것 같아요!');
insert into question(member_id, writer, title, contents)
values (2, 'finn.kakao', '오늘 점심 먹었습니다..', '점심밥으로는 초밥이 제일 무난한것 같아요!');

insert into reply(member_id, question_id, comment, writer)
values (1, 1, '피드가 좋네요! 댓글 남기고 갑니다!', 'jm.hong');
insert into reply(member_id, question_id, comment, writer)
values (2, 1, '정말 정신이 없네요..', 'finn.kakao');
