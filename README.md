# be-w34-spring-cafe

## 스프링카페 ver1.0

### 1단계

회원가입, 사용자 목록 기능 구현

- [X] 회원가입 기능 구현
    - [X] 사용자 관리 기능 구현을 담당할 UserController 클래스를 추가하고 애노테이션 매핑한다.
    - [X] @Controller 애노테이션 추가
    - [X] 회원가입하기 요청(POST 요청)을 처리할 메소드를 추가하고 매핑한다.
    - [X] @PostMapping 추가하고 URL 매핑한다.
    - [X] 사용자가 전달한 값을 User 클래스를 생성해 저장한다.
    - [X] 사용자 목록을 관리하는 HashMap을 생성한 후 앞에서 생성한 User 인스턴스를 HashMap에 저장한다.
    - [X] 사용자 추가를 완료한 후 사용자 목록 페이지("redirect:/user")로 이동한다.
- [X] 회원 목록 기능 구현
    - [X] Controller 클래스는 회원가입하기 과정에서 추가한 UserController를 그대로 사용한다.
    - [X] 회원목록 요청(GET 요청)을 처리할 메소드를 추가하고 매핑한다.
    - [X] @GetMapping을 추가하고 URL 매핑한다.
    - [X] Model을 메소드의 인자로 받은 후 Model에 사용자 목록을 users라는 이름으로 전달한다.
    - [X] 사용자 목록을 user/list.html로 전달하기 위해 메소드 반환 값을 "user/list"로 한다.
    - [X] user/list.html 에서 사용자 목록을 출력한다.
- [X] 회원 프로필 조회
    - [X] 앞 단계의 사용자 목록 html인 user/list.html 파일에 nickname 클릭하면 프로필 페이지로 이동하도록 한다.
    - [X] html에서 페이지 이동은 <a /> 태그를 이용해 가능하다.
    - [X] \<a href="/user/{{userId}}" />와 같이 구현한다.
    - [X] Controller 클래스는 앞 단계에서 사용한 UserController를 그대로 사용한다.
    - [X] 회원프로필 요청(GET 요청)을 처리할 메소드를 추가하고 매핑한다.
    - [X] @GetMapping을 추가하고 URL 매핑한다.
    - [X] URL은 "/user/{userId}"와 같이 매핑한다.
    - [X] URL을 통해 전달한 사용자 아이디 값은 @PathVariable 애노테이션을 활용해 전달 받을 수 있다.
    - [X] HashMap에 저장되어 있는 사용자 중 사용자 아이디와 일치하는 User 데이터를 Model에 저장한다.
    - [X] user/profile.html 에서는 Controller에서 전달한 User 데이터를 활용해 사용자 정보를 출력한다.

### 2단계

글쓰기, 글 목록 및 상세 보기 기능 구현

- [X] 글쓰기, 글 목록 기능 구현
    - [X] 게시글 기능 구현을 담당할 ArticleController를 추가하고 애노테이션 매핑한다.
    - [X] 게시글 작성 요청(POST 요청)을 처리할 메소드를 추가하고 매핑한다.
    - [X] 사용자가 전달한 값을 Article 클래스를 생성해 저장한다.
    - [X] 게시글 목록을 관리하는 HashMap를 생성한 후 앞에서 생성한 Article 인스턴스를 HashMap에 저장한다.
    - [X] 게시글 추가를 완료한 후 메인 페이지(“redirect:/”)로 이동한다.
- [X] 글 상세 보기 기능 구현
    - [X] 게시글 목록(article/list)의 제목을 클릭했을 때 게시글 상세 페이지에 접속할 수 있도록 한다.
    - [X] 게시글 상세 페이지 접근 URL은 "/articles/{index}"(예를 들어 첫번째 글은 /articles/1)와 같이 구현한다.
    - [X] 게시글 객체에 id 인스턴스 변수를 추가하고 ArrayList에 게시글 객체를 추가할 때 ArrayList.size() + 1을 게시글 객체의 id로 사용한다.
    - [X] Controller에 상세 페이지 접근 method를 추가하고 URL은 /articles/{index}로 매핑한다.
    - [X] HashMap에서 index에 해당하는 데이터를 조회한 후 Model에 저장해 /article/show.html에 전달한다.
    - [X] /article/show.html에서는 Controller에서 전달한 데이터를 활용해 html을 생성한다.