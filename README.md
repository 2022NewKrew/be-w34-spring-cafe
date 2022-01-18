# be-w34-spring-cafe

## logger
- https://www.baeldung.com/spring-boot-logging
- https://www.sangkon.com/hands-on-springboot-logging/
- https://commons.apache.org/proper/commons-logging/guide.html#Configuration
- 로깅전략
  - https://meetup.toast.com/posts/149 
  - https://tecoble.techcourse.co.kr/post/2020-07-30-use-logger/ 

## aop 적용하기 (log)
- https://lifere.tistory.com/185

## javaDocs
- https://docs.oracle.com/en/java/javase/11/docs/specs/doc-comment-spec.html
- https://blog.live2skull.kr/javadoc/java/java-javadoc/

## DTO에 대한 이름 고찰.
- 요청받을 때 , 반환할 때 DTO에 대한 이름 고찰..
- https://www.baeldung.com/java-dto-pattern
- MemberRequest
- MemberParameter
- MemberParam

## jdbcTemplate
- https://www.baeldung.com/spring-jdbc-jdbctemplate

## 로그인 관련을 공통관심사로 처래히보자!
- https://velog.io/@sungjin0757/SPRING-MVC-Filter-Interceptor-%EB%A1%9C%EA%B7%B8%EC%9D%B8-%EA%B4%80%EB%A0%A8-%EA%B6%8C%ED%95%9C-%EC%B2%98%EB%A6%AC

## Docker 연동
- https://perfectacle.github.io/2019/04/16/spring-boot-docker-image-optimization/

### open jdk docekr
```
docker build -t redbin/kakao-cafe . 
docker run --rm -d -p 8080:8080 --name demo redbin/kakao-cafe
```
### mysql build
```
docker run -d -p 3306:3306 -e MYSQL_ROOT_PASSWORD=1234 --name kakao-db -v /Users/kakao/Desktop/db:/var/lib/mysql mysql:5.7 --character-set-server=utf8mb4 --collation-server=utf8mb4_unicode_ci
docker exec -it kakao-db bash
```
## 그 밖의 코드 스타일에 관한 고찰들..
- https://zdnet.co.kr/view/?no=20150723103651
  - 80자의 굴래?
