INSERT INTO ARTICLE(writer, title, contents) VALUES ('mywriter', 'mytitle', 'mycontents');
SELECT id, writer, title, contents FROM ARTICLE;
INSERT INTO USERS(userid, password, name, email) VALUES ('myuserid', 'mypassword', 'myname', 'myemail@email.com');
INSERT INTO USERS(userid, password, name, email) VALUES ('myuserid2', 'mypassword2', 'myname2', 'myemail2@email.com');
SELECT userid, password, name, email FROM USERS;
