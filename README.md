# be-w34-spring-cafe

## 기능 요구사항

- 회원가입, 사용자 목록 기능 구현
### 회원가입 기능 구현
    - 회원하기 페이지는 static/user/form.html을 사용한다.
    - 사용자 관리 기능 구현을 담당할 UserController 클래스를 추가하고 애노테이션 매핑한다.
        - @Controller 애노테이션 추가
    - 회원가입하기 요청(POST 요청)을 처리할 메소드를 추가하고 매핑한다.
        - @PostMapping 추가하고 URL 매핑한다.
    - 사용자가 전달한 값을 User 클래스를 생성해 저장한다.
        - 회원가입할 때 전달한 값을 저장할 수 있는 필드를 생성한 후 setter와 getter 메소드를 생성한다.
    - 사용자 목록을 관리하는 ArrayList를 생성한 후 앞에서 생성한 User 인스턴스를 ArrayList에 저장한다.
    - 사용자 추가를 완료한 후 사용자 목록 페이지("redirect:/users")로 이동한다.
### 회원목록 기능 구현
    - 회원목록 페이지는 static/user/list.html을 사용한다.
    - static에 있는 html을 templates로 이동한다.
    - Controller 클래스는 회원가입하기 과정에서 추가한 UserController를 그대로 사용한다.
    - 회원목록 요청(GET 요청)을 처리할 메소드를 추가하고 매핑한다.
        - @GetMapping을 추가하고 URL 매핑한다.
    - Model을 메소드의 인자로 받은 후 Model에 사용자 목록을 users라는 이름으로 전달한다.
    - 사용자 목록을 user/list.html로 전달하기 위해 메소드 반환 값을 "user/list"로 한다.
    - user/list.html 에서 사용자 목록을 출력한다.
### 회원 프로필 조회
    - 회원 프로필 보기 페이지는 static/user/profile.html을 사용한다.
    - static에 있는 html을 templates로 이동한다.
    - 앞 단계의 사용자 목록 html인 user/list.html 파일에 닉네임을 클릭하면 프로필 페이지로 이동하도록 한다.
        - html에서 페이지 이동은 `<a />` 태그를 이용해 가능하다.
        - `<a href="/users/{{userId}}" />`와 같이 구현한다.
    - Controller 클래스는 앞 단계에서 사용한 UserController를 그대로 사용한다.
    - 회원프로필 요청(GET 요청)을 처리할 메소드를 추가하고 매핑한다.
        - @GetMapping을 추가하고 URL 매핑한다.
        - URL은 "/users/{userId}"와 같이 매핑한다.
    - URL을 통해 전달한 사용자 아이디 값은 @PathVariable 애노테이션을 활용해 전달 받을 수 있다.
    - ArrayList에 저장되어 있는 사용자 중 사용자 아이디와 일치하는 User 데이터를 Model에 저장한다.
    - user/profile.html 에서는 Controller에서 전달한 User 데이터를 활용해 사용자 정보를 출력한다.