# be-w34-spring-cafe

## 1-1단계
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
- [x] 회원 프로필 조회
  - [x] 회원 프로필 보기 페이지는 static/user/profile.html을 사용
  - [x] static에 있는 html을 templates로 이동
  - [x] 앞 단계의 사용자 목록 html인 user/list.html 파일에 닉네임을 클릭하면 프로필 페이지로 이동하도록 함
    - `<a href="/users/{{userId}}" />`와 같이 구현
  - [x] 회원프로필 요청(GET 요청)을 처리할 메소드를 추가하고, @GetMapping을 이용해 URL 매핑
    - `/users/{userId}`와 같이 매핑
    - 사용자 아이디 값은 @PathVariable 애노테이션을 활용해 전달
  - [x] ArrayList에 저장되어 있는 사용자 중 사용자 아이디와 일치하는 User 데이터를 Model에 저장
  - [x] user/profile.html 에서는 Controller에서 전달한 User 데이터를 활용해 사용자 정보를 출력
- [ ] HTML 중복 제거
- [ ] URL과 html 쉽게 연결하기
  - base package 아래에 config와 같은 새로운 패키지 생성
  - MvcConfig 이름으로 클래스를 생성해 구현

### 피드백
- [x] ControllerAdvice를 이용한 예외처리 및 에러 발생 시 보여주는 웹페이지 개발
- [x] repository에 들어가는 List와 Long을 동기화가 가능한 타입으로 변경
  - 참고자료
    - [volatile](https://jyami.tistory.com/112)
    - [Atomic Long](https://icthuman.tistory.com/entry/volatile%ED%82%A4%EC%9B%8C%EB%93%9C%EC%99%80-%EB%8F%99%EA%B8%B0%ED%99%94)
    - [synchronizedList](https://cornswrold.tistory.com/209)
- [x] @Valid를 이용해 request와 domain에서 validation 체크하기
  - [참고자료](https://jyami.tistory.com/55)

## 1-2단계
### 요구사항
- [x] 글쓰기 기능 구현
  - [x] 게시글 페이지는 static/qna/form.html을 수정해서 사용 
  - [x] static에 있는 html을 templates로 이동
  - [x] 게시글 기능 구현을 담당할 ArticleController를 추가하고 애노테이션 매핑
  - [x] 게시글 작성 요청(POST 요청)을 처리할 메소드를 추가하고 매핑
  - [x] 사용자가 전달한 값을 Article 클래스를 생성해 저장
  - [x] 게시글 목록을 관리하는 ArrayList를 생성한 후 앞에서 생성한 Article 인스턴스를 ArrayList에 저장 
  - [x] 게시글 추가를 완료한 후 메인 페이지(“redirect:/”)로 이동
- [x] 글 목록 기능 구현
  - [x] 메인 페이지(요청 URL이 “/”)를 담당하는 Controller의 method에서 게시글 목록을 조회
  - [x] 조회한 게시글 목록을 Model에 저장한 후 View에 전달한다. 게시글 목록은 앞의 게시글 작성 단계에서 생성한 ArrayList를 그대로 전달
  - [x] View에서 Model을 통해 전달한 게시글 목록을 출력한다.
    * 게시글 목록을 구현하는 과정은 사용자 목록을 구현하는 html 코드를 참고
- [x] 게시글 상세보기 기능 구현
  - [x] 게시글 목록(qna/list.html)의 제목을 클릭했을 때 게시글 상세 페이지에 접속할 수 있도록 함
    
    -> 제목이 아닌, 전체 열을 클릭했을 때로 변경
    - [x] 게시글 상세 페이지 접근 URL은 "/articles/{index}"
    - [x] 게시글 객체에 id 인스턴스 변수를 추가하고 ArrayList에 게시글 객체를 추가할 때 ArrayList.size() + 1을 게시글 객체의 id로 사용
      
      -> 게시글이 삭제될 때를 대비하여 별도로 Long타입을 만들어 주입
    - [x] Controller에 상세 페이지 접근 method를 추가하고 URL은 /articles/{index}로 매핑
    - [x] ArrayList에서 index - 1 해당하는 데이터를 조회한 후 Model에 저장해 /qna/show.html에 전달
    - [x] /qna/show.html에서는 Controller에서 전달한 데이터를 활용해 html을 생성
- [ ] (선택) 회원정보 수정 기능 구현
  - [ ] 회원가입한 사용자 정보를 수정할 수 있는 수정 화면과 사용자가 수정한 값을 업데이트할 수 있는 기능을 나누어 개발
  - [ ] /user/form.html 파일을 /user/updateForm.html로 복사한 후 수정화면을 생성
  - [ ] URL 매핑을 할 때 "/users/{id}/form"와 같이 URL을 통해 인자를 전달하는 경우 @PathVariable 애노테이션을 활용해 인자 값을 얻음
    - public String updateForm(@PathVariable String id)와 같이 구현
  - [ ] Controller에서 전달한 값을 입력 폼에서 출력하려면 value를 사용
    - `<input type="text" name="name" value="{{name}}" />`

## 1-3단계
### 요구사항
- [x] 사용자 데이터를 DB를 저장
  - [x] ORM은 사용하지 않음
  - [x] Spring JDBC를 사용 (DB는 H2를 사용)
  - [x] DB 저장 및 검색에 필요한 SQL은 직접 작성
- [x] 게시글 데이터 저장
  - [x] Article 클래스를 DB 테이블에 저장할 수 있게 구현
  - [x] Article 테이블이 적절한 PK를 가지도록 구현
- [x] 게시글 목록 구현
  - 전체 게시글 목록 데이터를 DB에서 조회하도록 구현
- [x] 게시글 상세보기 구현
  - 게시글의 세부 내용을 DB에서 가져오도록 구현

## 2-1단계
### 요구사항
- [x] 로그인 기능 구현
  - 로그인이 가능해야 함
  - 현재 상태가 로그인 상태이면 상단 메뉴가 “로그아웃”, “개인정보수정”이 나타나야 하며, 로그아웃 상태이면 상단 메뉴가 “로그인”, “회원가입”이 나타나야 함

- [ ] (선택) 개인정보 수정 요구사항 추가
  - 로그인한 사용자는 자신의 정보를 수정할 수 있어야 함
  - 이름, 이메일만 수정할 수 있으며, 사용자 아이디는 수정할 수 없음
  - 비밀번호가 일치하는 경우에만 수정 가능
### 피드백
- [x] HttpSession을 이용한 공통 로직을 Spring Interceptor혹은 aop를 사용해 제거
- [x] 롬복 사용 시 주의사항을 참고하며 Builder 적용해보기
  - [참고자료](https://cheese10yun.github.io/lombok/)

## 2-2단계
### 요구사항
- [x] 로그인하지 않은 사용자는 게시글의 목록만 볼 수 있다.
- [x] 로그인한 사용자만 게시글의 세부내용을 볼 수 있다.
- [x] 로그인한 사용자만 게시글을 작성할 수 있다.
- [x] 로그인한 사용자는 자신의 글을 수정 및 삭제할 수 있다.
- [ ] 데이터베이스를 MySQL로 변경한다.
  - 서버에 MySQL을 설치하는 경험을 해 본다. 
  - 프로젝트 구성은 필요에 따라 로컬 설치, 도커를 통한 설치, 서버에 설치 중 편한 방법을 선택해서 연동한다.
