insert into member(user_id, password, name, email)
values ('jm.hong', 'jm.hong', 'Hongjeongmin', 'jm.hong@kakaocorp.com');
insert into member(user_id, password, name, email)
values ('finn.kakao', 'finn.kakao', 'Kimkakao', 'finn.kakao@kakaocorp.com');

insert into question(member_id, writer, title, contents)
values (1, 'jm.hong', '안녕하세요 반갑습니다.', '오늘 날씨가 좋네요. 정말 날시가 좋은것 같아요!');
insert into question(member_id, writer, title, contents)
values (2, 'finn.kakao', '오늘 점심 먹었습니다..', '점심밥으로는 초밥이 제일 무난한것 같아요!');
