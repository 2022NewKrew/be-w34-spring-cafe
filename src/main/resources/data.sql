select * from USER_PROFILE;

insert into USER_PROFILE(user_id, password, name, email)
values ('lucas', '123', 'tt', 'tt@test.com'), ('lucas2', '123', 'ttt', 'ttt@test.com');

insert into QNA(writer, title, contents)
values ('lucas', 'test1', 'test1 contents'), ('lucas2', 'test2', 'test2 contents');
