DROP TABLE IF EXISTS users CASCADE;
DROP TABLE IF EXISTS articles CASCADE;

CREATE TABLE users (
  seq           bigint NOT NULL AUTO_INCREMENT,          --사용자 PK
  id            varchar(10) NOT NULL,           --사용자 ID
  password      varchar(80) NOT NULL,           --로그인 비밀번호
  name          varchar(10) NOT NULL,           --사용자명
  email         varchar(50) NOT NULL,           --로그인 이메일
  login_count   int NOT NULL DEFAULT 0,         --로그인 횟수. 로그인시 마다 1 증가
  last_login_at datetime DEFAULT NULL,          --최종 로그인 일자
  time     datetime NOT NULL DEFAULT CURRENT_TIMESTAMP(),
  PRIMARY KEY (seq),
  CONSTRAINT unq_user_name UNIQUE (id)
);

CREATE TABLE articles (
  seq           bigint NOT NULL AUTO_INCREMENT,          --질문 PK
  user_seq      bigint NOT NULL,                --질문 작성자 PK (users 테이블 참조)
  writer        varchar(10) NOT NULL,           --질문 작성자
  title         varchar(100) NOT NULL,          --질문 제목
  content       varchar(1000) NOT NULL,         --질문 내용
  deleted       bool DEFAULT FALSE,             --질문 제거 유무
  time     datetime NOT NULL DEFAULT CURRENT_TIMESTAMP(),
  PRIMARY KEY (seq),
  CONSTRAINT fk_articles_to_users FOREIGN KEY (user_seq) REFERENCES users (seq) ON DELETE RESTRICT ON UPDATE RESTRICT
);

CREATE TABLE replys (
  seq           bigint NOT NULL AUTO_INCREMENT,          --댓글 PK
  user_seq      bigint NOT NULL,                --댓글 작성자 PK (users 테이블 참조)
  article_seq   bigint NOT NULL,                --댓글 작성자 PK (articles 테이블 참조)
  writer        varchar(10) NOT NULL,           --댓글 작성자
  content       varchar(1000) NOT NULL,         --댓글 내용
  deleted       bool DEFAULT FALSE,             --댓글 제거 유무
  time     datetime NOT NULL DEFAULT CURRENT_TIMESTAMP(),
  PRIMARY KEY (seq),
  CONSTRAINT fk_replys_to_users FOREIGN KEY (user_seq) REFERENCES users (seq) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT fk_replys_to_articles FOREIGN KEY (article_seq) REFERENCES articles (seq) ON DELETE RESTRICT ON UPDATE RESTRICT
);
