# be-w34-spring-cafe

## 1단계
### 주의사항
- 정적인(static) HTML은 static 폴더에서 관리
- 동적인(dynamic) HTML은 templates 디렉토리에서 관리
### 요구사항
- [x] 웹페이지 디자인 (by austin.22)
  - [디자인 페이지 참고자료](https://www.figma.com/file/nwhBasptomWJCAMkElxp74/%EC%9E%90%EB%B0%94%EB%B0%B1%EC%97%94%EB%93%9C%EA%B5%90%EC%9C%A1%EC%9A%A9%EC%9B%B9%ED%8E%98%EC%9D%B4%EC%A7%80?node-id=0%3A1)
- [x] 회원가입 기능 구현
  - [x] 회원하기 페이지는 static/user/signup.html을 사용
  - [x] static에 있는 html을 templates로 이동
  - [x] 사용자 관리 기능 구현을 담당할 UserController 클래스를 추가하고 @Controller 매핑
  - [x] 회원가입하기 요청(POST 요청)을 처리할 메소드를 추가하고, @PostMapping을 이용해 URL 매핑
  - [x] 사용자가 전달한 값을 User 클래스를 생성해 저장
    - 회원가입할 때 전달한 값을 저장할 수 있는 필드를 생성한 후 setter와 getter 메소드를 생성
  - [x] 사용자 목록을 관리하는 ArrayList를 생성한 후 앞에서 생성한 User 인스턴스를 ArrayList에 저장
  - [x] 사용자 추가를 완료한 후 사용자 목록 페이지("redirect:/users")로 이동
- [x] 회원목록 기능 구현
  - [x] 회원목록 페이지는 static/user/list.html을 사용
  - [x] static에 있는 html을 templates로 이동
  - [x] 회원목록 요청(GET 요청)을 처리할 메소드를 추가하고, @GetMapping을 이용해 URL 매핑
  - [x] Model을 메소드의 인자로 받은 후 Model에 사용자 목록을 users라는 이름으로 전달
  - [x] 사용자 목록을 user/list.html로 전달하기 위해 메소드 반환 값을 "user/list"로 함
  - [x] user/list.html 에서 사용자 목록을 출력
- [ ] 회원 프로필 조회
  - [ ] 회원 프로필 보기 페이지는 static/user/profile.html을 사용
  - [ ] static에 있는 html을 templates로 이동
  - [ ] 앞 단계의 사용자 목록 html인 user/list.html 파일에 닉네임을 클릭하면 프로필 페이지로 이동하도록 함
    - `<a href="/users/{{userId}}" />`와 같이 구현
  - [ ] 회원프로필 요청(GET 요청)을 처리할 메소드를 추가하고, @GetMapping을 이용해 URL 매핑
    - `/users/{userId}`와 같이 매핑
    - 사용자 아이디 값은 @PathVariable 애노테이션을 활용해 전달
  - [ ] ArrayList에 저장되어 있는 사용자 중 사용자 아이디와 일치하는 User 데이터를 Model에 저장
  - [ ] user/profile.html 에서는 Controller에서 전달한 User 데이터를 활용해 사용자 정보를 출력
- [ ] HTML 중복 제거
- [ ] URL과 html 쉽게 연결하기
  - base package 아래에 config와 같은 새로운 패키지 생성
  - MvcConfig 이름으로 클래스를 생성해 구현
