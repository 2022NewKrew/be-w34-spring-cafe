insert into USERS (userid, password, name, email) values ('admin', 'admin', 'adminname', 'admin@kakaocorp.com');
insert into USERS (userid, password, name, email) values ('firstid', '1234', 'firstname', 'first@kakaocorp.com');
insert into ARTICLES (userid, name, title, contents, createdAt) values ('admin', 'adminname', 'firsttitle', 'firstcontents', CURRENT_TIMESTAMP());
insert into ARTICLES (userid, name, title, contents, createdAt) values ('firstid', 'firstname', 'secondtitle', 'secondcontents', CURRENT_TIMESTAMP());
