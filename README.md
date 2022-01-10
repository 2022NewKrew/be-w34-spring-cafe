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
    - [X] 앞 단계의 사용자 목록 html인 user/list.html 파일에 username을 클릭하면 프로필 페이지로 이동하도록 한다.
    - [X] html에서 페이지 이동은 <a /> 태그를 이용해 가능하다.
    - [X] <a href="/user/{{username}}" />와 같이 구현한다.
    - [X] Controller 클래스는 앞 단계에서 사용한 UserController를 그대로 사용한다.
    - [X] 회원프로필 요청(GET 요청)을 처리할 메소드를 추가하고 매핑한다.
    - [X] @GetMapping을 추가하고 URL 매핑한다.
    - [X] URL은 "/user/{username}"와 같이 매핑한다.
    - [X] URL을 통해 전달한 사용자 아이디 값은 @PathVariable 애노테이션을 활용해 전달 받을 수 있다.
    - [X] HashMap에 저장되어 있는 사용자 중 사용자 아이디와 일치하는 User 데이터를 Model에 저장한다.
    - [X] user/profile.html 에서는 Controller에서 전달한 User 데이터를 활용해 사용자 정보를 출력한다.
