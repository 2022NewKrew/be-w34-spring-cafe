# be-w34-spring-cafe
* 작업 명세(완료한 것에 O표시)
  * step1-1
    * Logger 사용 O
    * get, post 테스트 O
    * 역할에 따른 패키지 분리 O
      * controller : 요청이 들어오면 추상화된 동작 지시
      * domain : 관련된 모든 데이터 가지고 있는 객체
      * dto : 서비스에 쓰이는 데이터만 가지고 있는 객체
      * view : 보여주는 로직 구현
      * service : 비즈니스 로직 구현
      * repository : DB에 데이터를 저장
    * 회원목록 기능 구현 O
      * 회원목록 controller
      * User domain
      * UserRepository repository
        * index(DB에 들어온 순서대로 가지는 고유의 번호) 생성은 여기에서
      * getUserList service
        * userList domain은 List에서 더 추가해야할 기능이 없는 거 같아서 구현 안함
      * UserView view
        * UserController에서 UserView로 model 전달
        * UserDao List 반환시 순서를 책임지는 건 view 로직에서 해결하도록 해야함
    * 회원가입 기능 구현 O
      * 회원가입 controller
      * createUser service
      * userRepository에 insert를 호출할 때 내부에서 DAO로 바꾸는 작업
    * 회원 프로필 조회 O
      * 회원목록에 하이퍼링크
      * profile.mustache로 이동 및 수정
      * userProfileDto로 최소한의 데이터만 담음
    * static, template 분리 O
      * mustache 생성
      * html 확장자로도 mustache 사용하는 환경설정
      * 중복되는 html요소들 template 분리
      * html의 하이퍼링크들을 상대경로를 절대경로로 바꾸기
        * 상대경로로 하면 html위치에 따라 에러가 쉽게 생김
      * controller에 로그인, 회원가입, 회원명단, 메인페이지에 대한 get요청을 명시적으로 표현
        * 직접적인 파일 경로참조 지양
  * step1-2
    * 게시글 목록 O
      * article 관련 모든 정보
        * writer, title, contents, number, timestamp
      * controller에서 service, view를 호출
      * service에서 repository, transformation 호출
      * view에선 추가적인 뷰 로직 구현(sort)
      * index 템플릿 수정
    * 게시글 상세보기 O
    * 게시글 쓰기 O
      * 게시글 템플릿 분리
      * service에 createArticle
