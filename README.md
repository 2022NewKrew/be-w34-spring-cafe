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
  * 구현할 시간이 없는 관계로 step1-3 생략
  * step2-1
    * 로그인 기능 O
      * login 홈페이지(get)
      * login 서비스(post)
      * 암호 비교는 service 에서
      * 로그인 상태에 따라 로그아웃, 개인정보수정 표시 혹은 로그인, 회원가입 표시
      * logout 기능
  * step2-2
    * 로컬 도커 MySQL 설정 O
      * docker-compose.yml 세팅
      * docker-compose로 mysql 컨테이너 실행
      * mysql_secure_installation로 기본적인 보안 설정
      * 데이터베이스 cafe 생성
      * spring 사용자 생성
      * spring 사용자 권한
    * 어플리케이션 MySQL과 연동하는지 테스트 O
      * jdbc template를 빈에서 가져오도록 service, controller, repository 어노테이션 사용
      * 어플리케이션 MySQL연동 세팅(url, username, password)
      * 테스트 쿼리 작성
      * 실행후 데이터베이스가 변했는지 확인
