# be-w34-spring-cafe
* 작업 명세(완료한 것에 O표시)
  * Logger 사용 O
  * get, post 테스트 O
  * 역할에 따른 패키지 분리 O
    * controller : 요청이 들어오면 추상화된 동작 지시
    * domain : dto, 데이터만 가지고 있는 객체
    * view : 보여주는 로직 구현
    * service : 비즈니스 로직 구현
    * repository : DB에 데이터를 저장
  * 회원가입 기능 구현
  * 회원목록 기능 구현
  * 회원 프로필 조회
  * static, template 분리
    * mustache 생성
    * html 확장자로도 mustache 사용하는 환경설정
