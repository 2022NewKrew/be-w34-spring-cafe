# be-w34-spring-cafe
- 3주차 실습 과제 (2022-01-10 ~ 2022-01-14)

# 수행 리스트
- [x] Controller 연결
  - [x] Controller 생성 ( 어노테이션 )
  - [x] MappingURL 관리 enum 생성
- [x] template 구현
  - [x] static 의 html 파일을 template 으로 이동
    - [x] configuration 설정
    - [x] application.properties 설정
- [x] logger 설정하기
- [x] 사용자 관련 기능
  - [x] Service 처리
    - [x] Service 객체 생성 ( 어노테이션 )
    - [x] Service 연결 및 호출
  - [x] Dao 처리
    - [x] 사용자 정보를 저장, 반환
    - [x] 로컬메모리로 구현된 내용을 DB로 수정
  - [x] 회원가입 기능 구현
    - [x] Controller -> Service -> Dao 순서의 호출
    - [x] Dao 의 사용자 정보에 사용자 존재 여부 확인
    - [x] 신규 사용자인 경우에만 정상적으로 추가
    - [x] 추가에 실패하는 경우, 실패정보를 반환
  - [x] 회원 목록 기능 구현
    - [x] templates 이식
    - [x] 회원이 없는 경우, 빈 목록을 출력
    - [x] 비밀번호가 노출되지 않도록 수정
  - [x] 회원 프로필 조회
    - [x] templates 이식
    - [x] 특정 회원의 정보를 출력
    - [x] 존재하지 않는 회원의 경우, "존재하지 않는다" 는 정보를 출력
    - [x] 비밀번호가 노출되지 않도록 수정
- [x] URL 처리
  - [x] Enum 으로 URL 주소를 상수화
  - [x] 내부에 담은 변수들을 하나의 Domain 으로 분류하여, 재사용 가능하도록 수정
  - [x] 일부 Default URL 의 경우 동시관리가 힘들어서, 분리가 필요할 것 같음 ( 현재는 매직리터럴로 작성 )
    - [x] 화면을 전부 template 쪽으로 이동
    - [x] 접근용도의 GET Mapping 을 모아둔 클래스 작성하기
- [x] 게시글 관련 기능
  - [x] Service 처리
  - [x] Dao 처리
  - [x] 게시글 작성 기능 구현
  - [x] 게시글 목록 기능 구현
  - [x] 게시글 상세보기

# 수정 사항
- form.html 의 form 태그를 요구사항에 맞게 개선 ( get -> post 로 수정 )
- 경로는 예제에 나온 것처럼 "/users" 가 아닌, "/user" 기반
- Router 가 아니라 Controller 라는 명칭을 사용한다.
- Configuration 사용을 위해선 WebMvcConfigurer 를 implements 하고, @Configuration 작성 필요
- Mustache 가 적용되지 않던 이유는, 외부 주소에서 .html 파일을 직접 접근하는 형태였기 떄문
  - 템플릿 적용이 되지않고 문서 자체가 반환되어 생긴 문제로, 반환 문자열의 확장자 .html 을 제거하여 해결
- 추가로 MvcConfiguration 에서 "addResourceHandlers" 는 외부에서 templates 에 접근할 수 있도록 작성된 코드이므로, 삭제
  - 대신 mustache 적용이 되도록 "MustacheViewResolver" 적용이 반드시 필요 
  - application.prop 로도 해당 기능을 적용 가능 ( 이 경우,. @Configuration 클래스는 불필요 )
- Configuration 파일의 "addViewController" 의 경우, get/post 접근을 구분하지 못함
  - Controller 에 Get 맵핑을 추가하는 형태로 재작
- H2 연결 문제로 상당히 고전함
  1. 서버 설정 시, url 형태로 고전함 ( 아마, @Bean 으로 설정하지 않으면 생성되는 어떤 규칙에 따른 듯 함 )
  2. 쿼리문에서 사용한 테이블 명칭으로 인해 오류 발생 ( users 라는 db를 자동생성해서, 발생한 문제 )
  3. 문자열 처리로 인한 문제 ( " 가 아닌, ' 로 감싸야 에러가 없음 )
- param 이 자동 맵핑되는 경우, 기본 생성자 + setter / 혹은 인자가 정확히 들어맞는 생성자가 반드시 필요
- RequestBody 의 맵핑의 경우, 들어오는 값의 조합으로 만들 수 있는 오버라이딩 생성자가 반드시 필요함
  - 예를들어, A, B, C, D 라는 멤버변수를 가진 클래스와, B,C 라는 요소만을 입력으로 갖는 요청이 있는 경우
  - B,C 의 조합으로 만들 수 있는 오버라이딩 생성자가 필요 ( 생성자(A) / 생성자(B) / 생성자(A,B) )
  - 생성자로 초기화되지 못한 값은 setter 로 자동 삽입
  - 위의 조건을 만족하는 유일한 생성자가 반드시 필요하고, 다수의 생성자가 필요한 경우 기본 생성자를 사용해야 함
    - No primary or single unique constructor found for class
- mustache 에서 Session 객체에 접근하려면 추가설정이 필요
  - application.properties 에 "spring.mustache.expose-session-attributes=true" 설정이 필요
- @Aspect 를 쓰기 위해선, spring-aop 가 아닌 spring-boot-starter-aop 가 필요
  - Get/Post Mapping 의 경우, annotation 기반으로 적용시켜서 되는데, execute 는 왜 실행이 안되는지 잘 파악을 못하겠음
- mysql 과의 연동과정에서, application.properties 설정문제가 발생
  - hikari 라는 도구가 스프링의 기본툴로 잡혀있음
  - 그래서 인자 명칭에 hikari 라는 경로명이 추가되었으나, 에러가 발생
    - HikariPool-1 - jdbcUrl is required with driverClassName.
      - jdbc-url 에는 hikari 경로를 제거하니 일단은 해결됨
      - 확인해보니, 하단에 정의된 h2 선언이 해당 내용을 덧씌워서 생긴 오류 ( h2 설정을 주석처리 )
- 테이블이 존재해여 접근이 가능하므로, workbench 나 cli 로 테이블을 미리 생성해줘야함
  - h2 랑 다르게 스크립트를 추가시켜 같이 실행시키는 기능은 따로 없는 것 같음
- Dotenv 로 mysql 비밀번호를 좀 보호하려고 했는데, spring 환경에선 적용이 안되는 듯 하다.
  - 관련 모듈을 발견했는데, 적용하긴 좀 껄끄러운 상태
- sql를 사용하려면 먼저 server 를 켜야함 ( mysql.server start )
- regex 에서 "." 는 줄바꿈 문자를 잡아내지 못함
  - 엔터가 들어간 데이터에 대해서는 검증 오류가 발생했음
  - [\s\S] 를 사용하여 모든 문자에 대해 허용하도록 수정

# 배포 절차
- 실패한 방법
  - File -> project structure -> artifacts 추가
  - Build -> build artifacts
  - cmd 창에서 "kinit 으로 로그인"
  - "scp cafe.main.jar deploy@FQDN값:/home/deploy"
  - "java -jar jar파일.jar" 로 실행
- 성공한 방법
  - ./gradlew build
  - /build/libs 내에 존재하는 스냅샷을 scp 로 전달
  - "java -jar" 로 실행
- 서버까지는 뜨지만, 화면이 정상출력되지 않음
  - "logging.level.root=debug" 를 프로퍼티에 적용 후, 디버그 로그를 직접 확인
  - 출력하는 .html 파일 경로에 /가 중첩으로 들어간 것을 확인 ( templates//path/~.html )
  - 아무래도, spring 이 template/ 까지를 기본 경로로 추가해줘서 생긴 문제 같음 (로컬에선 왜 돌아간건지 의문)
  - 각 View 경로의 앞에 "/" 를 제거해주고, 리빌딩하여 서버에서 작동시키니 정상동작함

# 참고 사이트
- Collection 관련
  - https://mkyong.com/java8/java-8-convert-map-to-list/
- Mustache 관련
  - https://gs.saro.me/dev?tn=486
  - https://velog.io/@swchoi0329/Mustache%EB%A1%9C-%ED%99%94%EB%A9%B4-%EA%B5%AC%EC%84%B1%ED%95%98%EA%B8%B0#template-engine
  - https://doublesprogramming.tistory.com/165
- regex 정규표현식
  - https://docs.oracle.com/javase/7/docs/api/java/util/regex/Pattern.html
  - https://stackoverflow.com/questions/3222649/any-character-including-newline-java-regex/25071906
- H2 데이터베이스
  - https://seungyooon.tistory.com/218
  - https://velog.io/@jwkim/spring-boot-datajpatest-error
- slf4j
  - https://programmer93.tistory.com/64
- aop
  - https://vmpo.tistory.com/100
  - https://nankisu.tistory.com/6
  - https://velog.io/@shson/%EC%8A%A4%ED%94%84%EB%A7%81Spring-AOP-AspectJ-Pointcut-%ED%91%9C%ED%98%84%EC%8B%9D-1-1-execution
- scp
  - https://eehoeskrap.tistory.com/543
- java sdk
  - https://hoohaha.tistory.com/41
- mysql
  - https://whitepaek.tistory.com/16
  - https://cirius.tistory.com/1769
  - https://dev-coco.tistory.com/85
  - https://doozi0316.tistory.com/entry/Spring-Boot-MyBatis-MySQL-%EC%97%B0%EB%8F%99-%EB%B0%A9%EB%B2%95
  - https://offbyone.tistory.com/54
  - https://deviscreen.tistory.com/85
  - https://engkimbs.tistory.com/794 ( 실패 )
  - https://m.blog.naver.com/jesang1/221993846056
  - https://velog.io/@taelee/mysql-%EC%84%9C%EB%B2%84-%EC%8B%9C%EC%9E%91-%EB%98%90%EB%8A%94-%EC%9E%AC%EC%8B%9C%EC%9E%91-%ED%95%98%EA%B8%B0MAC
- dotenv
  - http://daplus.net/java-spring-boot%EC%9D%98-application-properties%EC%97%90%EC%84%9C-env-%EB%B3%80%EC%88%98-%EC%82%AC%EC%9A%A9/
  - https://wordbe.tistory.com/entry/Springboot-%EC%99%B8%EB%B6%80%EC%84%A4%EC%A0%95-%ED%94%84%EB%A1%9C%ED%8C%8C%EC%9D%BC
  - https://github.com/paulschwarz/spring-dotenv
