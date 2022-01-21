#FROM mdock.daumkakao.io/mysql:5.7
FROM mysql:5.7
ADD ./mysql-init-files /docker-entrypoint-initdb.d

EXPOSE 3306 33060

ENV MYSQL_ROOT_PASSWORD test
ENV MYSQL_DATABASE KAKAO_CAFE

CMD ["mysqld", "--character-set-server=utf8", "--collation-server=utf8_general_ci"]
