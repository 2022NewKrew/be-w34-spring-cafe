INSERT INTO `user` (`nickname`,`password`,`name`, `email`) VALUES ('test', 'test', 'test', 'test@test.com');
INSERT INTO `user` (`nickname`,`password`,`name`, `email`) VALUES ('javajigi', 'test', '자바지기', 'javajigi@slipp.net');
INSERT INTO `user` (`nickname`,`password`,`name`, `email`) VALUES ('sanjigi', 'test', '산지기', 'sanjigi@slipp.net');

INSERT INTO `question` (`writer`, `title`, `contents`) VALUES (1, '제목1', '내용1'), (1, '제목2', '내용2');

INSERT INTO `reply` (`question_id`, `user_id`, `contents`) VALUES(1,1,'답변1-1'), (1,1,'답변1-2'), (2,1,'답변2-1');