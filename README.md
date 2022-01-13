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
- [ ] logger 설정하기
- [-] 사용자 관련 기능
  - [x] Service 처리
    - [x] Service 객체 생성 ( 어노테이션 )
    - [x] Service 연결 및 호출
  - [x] Dao 처리
    - [x] 사용자 정보를 저장, 반환
    - [x] 로컬메모리로 구현된 내용을 DB로 수정
  - [-] 회원가입 기능 구현
    - [x] Controller -> Service -> Dao 순서의 호출
    - [x] Dao 의 사용자 정보에 사용자 존재 여부 확인
    - [x] 신규 사용자인 경우에만 정상적으로 추가
    - [ ] 추가에 실패하는 경우, 실패정보를 반환
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
- [-] 게시글 관련 기능
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

# 참고 사이트
- Collection 관련
  - https://mkyong.com/java8/java-8-convert-map-to-list/
- Mustache 관련
  - https://gs.saro.me/dev?tn=486
  - https://velog.io/@swchoi0329/Mustache%EB%A1%9C-%ED%99%94%EB%A9%B4-%EA%B5%AC%EC%84%B1%ED%95%98%EA%B8%B0#template-engine
- regex 정규표현식
  - https://docs.oracle.com/javase/7/docs/api/java/util/regex/Pattern.html
- H2 데이터베이스
  - https://seungyooon.tistory.com/218
  - https://velog.io/@jwkim/spring-boot-datajpatest-error
