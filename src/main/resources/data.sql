insert into MEMBER (USER_ID, USER_NAME, PASSWORD, EMAIL)
VALUES ('kong', '공돈욱', 'Ab12345!', 'wooky9633@naver.com');
insert into MEMBER (USER_ID, USER_NAME, PASSWORD, EMAIL)
VALUES ('rubam', '루밤', 'Ab12345!', 'rubam.kong@kakaocorp.com');

insert into ARTICLE (TITLE, TEXT, AUTHOR_ID)
VALUES ('hello', 'hello world! my name is rubam', 2);

select a.title, a.text, a.time, m.member_id, m.user_id, m.user_name
from ARTICLE as a
         inner JOIN MEMBER as m ON a.author_id = m.member_id;
