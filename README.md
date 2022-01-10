# be-w34-spring-cafe
- 3주차 실습 과제 (2022-01-10 ~ 2022-01-14)

# 수행 리스트
- [x] Controller 연결
  - [x] Controller 생성 ( 어노테이션 )
  - [x] MappingURL 관리 enum 생성
- [x] template 구현
  - [x] static 의 html 파일을 template 으로 이동
    - [x] configuration 설정
- [ ] Service 처리
  - [ ] Service 객체 생성 ( 어노테이션 )
  - [ ] Service 연결 및 호출
- [ ] 회원가입 기능 구현
  - [x] Controller -> Service -> Dao 순서의 호출
  - [ ] Dao 의 사용자 정보에 사용자 존재 여부 확인
  - [ ] 신규 사용자인 경우에만 정상적으로 추가
  - [ ] 추가에 실패하는 경우, 실패정보를 반환
- [ ] 회원 목록 기능 구현
- [ ] 회원 프로필 조회

# 수정 사항
- form.html 의 form 태그를 요구사항에 맞게 개선 ( get -> post 로 수정 )
- 경로는 예제에 나온 것처럼 "/users" 가 아닌, "/user" 기반
- Router 가 아니라 Controller 라는 명칭을 사용한다.
- Configuration 사용을 위해선 WebMvcConfigurer 를 implements 하고, @Configuration 작성 필요

# 참고 사이트
- https://gs.saro.me/dev?tn=486
- https://velog.io/@swchoi0329/Mustache%EB%A1%9C-%ED%99%94%EB%A9%B4-%EA%B5%AC%EC%84%B1%ED%95%98%EA%B8%B0#template-engine
