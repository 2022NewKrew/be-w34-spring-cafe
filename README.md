# be-w34-spring-cafe

## Step 1-1

### 회원가입, 사용자 목록 기능 구현
* 카페 깃헙 저장소를 기반으로 실습을 진행한다.
* Lucas 브랜치 생성 기능이 오류날 경우 깃헙 저장소에서 직접 브랜치를 생성한다. (현재 미리 생성되어 있음)
* 요구사항에 대한 구현을 완료한 후 자신의 github 아이디에 해당하는 브랜치에 Pull Request를 통해 리뷰 요청을 한다.

### 학습 참고 자료
* https://github.com/spring-projects/spring-petclinic
* https://jojoldu.tistory.com/250

### 웹페이지 디자인
* static 폴더에 있는 기존 자료(QA 게시판) 를 수정하거나 아래 디자인 페이지를 참고해서 구현한다.
* [디자인 페이지 참고자료](https://www.figma.com/file/nwhBasptomWJCAMkElxp74/%EC%9E%90%EB%B0%94%EB%B0%B1%EC%97%94%EB%93%9C%EA%B5%90%EC%9C%A1%EC%9A%A9%EC%9B%B9%ED%8E%98%EC%9D%B4%EC%A7%80?node-id=0%3A1)

### 각 기능에 따른 url과 메서드 convention
* 먼저 각 기능에 대한 대표 url을 결정한다.
  * 예를 들어 회원 관리와 관련한 기능의 대표 URL은 "/users"와 같이 설계한다.
* 기능의 세부 기능에 대해 일반적으로 구현하는 기능(목록, 상세보기, 수정, 삭제)에 대해서는 URL convention을 결정한 후 사용할 것을 추천한다.
  * 예를 들어 todo 앱에 대한 URL convention을 다음과 같은 기준으로 잡을 수 있다.

| url                      | 	기능                           |
|--------------------------|-------------------------------|
| GET /todos               | 	List all todos               |
| POST /todos              | 	Create a new todo            |
| GET /todos/:id           | 	Get a todo                   |
| PUT /todos/:id           | 	Update a todo                |
| DELETE /todos/:id        | 	Delete a todo and its items  |
| GET /todos/:id/items     | 	Get a todo item              |
| PUT /todos/:id/items     | 	Update a todo item           |
| DELETE /todos/:id/items  | 	Delete a todo item           |

## Step 1-2

### 글쓰기 및 글 목록 기능 구현
* 사용자는 게시글을 작성할 수 있어야 한다.

#### 힌트1 : 구현 단계별 힌트
* 게시글 페이지는 static/qna/form.html을 수정해서 사용한다.
* static에 있는 html을 templates로 이동한다.
* 게시글 기능 구현을 담당할 ArticleController를 추가하고 애노테이션 매핑한다.
* 게시글 작성 요청(POST 요청)을 처리할 메소드를 추가하고 매핑한다.
* 사용자가 전달한 값을 Article 클래스를 생성해 저장한다.
* 게시글 목록을 관리하는 ArrayList를 생성한 후 앞에서 생성한 Article 인스턴스를 ArrayList에 저장한다.
* 게시글 추가를 완료한 후 메인 페이지(“redirect:/”)로 이동한다.

### 글 목록 요구사항
* 모든 사용자는 게시글을 볼 수 있어야 한다.

#### 힌트
* 메인 페이지(요청 URL이 “/”)를 담당하는 Controller의 method에서 게시글 목록을 조회한다.
* 조회한 게시글 목록을 Model에 저장한 후 View에 전달한다. 게시글 목록은 앞의 게시글 작성 단계에서 생성한 ArrayList를 그대로 전달한다.
* View에서 Model을 통해 전달한 게시글 목록을 출력한다.
  * 게시글 목록을 구현하는 과정은 사용자 목록을 구현하는 html 코드를 참고한다.

### 게시글 상세보기
* 모든 사용자는 게시글 상세 내용을 볼 수 있어야 한다.

#### 힌트
* 게시글 목록(qna/list.html)의 제목을 클릭했을 때 게시글 상세 페이지에 접속할 수 있도록 한다.
  * 게시글 상세 페이지 접근 URL은 "/articles/{index}"(예를 들어 첫번째 글은 /articles/1)와 같이 구현한다.
  * 게시글 객체에 id 인스턴스 변수를 추가하고 ArrayList에 게시글 객체를 추가할 때 ArrayList.size() + 1을 게시글 객체의 id로 사용한다.
* Controller에 상세 페이지 접근 method를 추가하고 URL은 /articles/{index}로 매핑한다.
* ArrayList에서 index - 1 해당하는 데이터를 조회한 후 Model에 저장해 /qna/show.html에 전달한다.
* /qna/show.html에서는 Controller에서 전달한 데이터를 활용해 html을 생성한다.

## Step 1-3

### 사용자 데이터를 DB에 저장
* ORM은 사용하지 않는다.
* Spring JDBC를 사용한다.
* DB 저장 및 검색에 필요한 SQL은 직접 작성한다.

### 배포하기
* 사내 서버(Krane)을 이용해서 배포 실습을 진행한다.
* [Krane 접속 가이드](https://docs.google.com/document/d/1KautiZ6i57Brd7zG468VamOM5FFMHH28zEwOtzGJx7U/edit#heading=h.6ve4m3bphoxn)
* [Open JDK 설치 가이드](https://docs.google.com/document/d/1f81XSow0w9vVSpuFX01rCfraO0Cm5NzifId232SMTjQ/edit#)

### 실습 - 게시글 데이터를 DB에 저장 및 조회

#### 게시글 데이터 저장하기
* Article 클래스를 DB 테이블에 저장할 수 있게 구현한다.
* Article 테이블이 적절한 PK를 가지도록 구현한다.

#### 게시글 목록 구현하기
* 전체 게시글 목록 데이터를 DB에서 조회하도록 구현한다.

#### 게세글 상세보기 구현하기
* 게시글의 세부 내용을 DB에서 가져오도록 구현한다.

### (선택) 회원정보 수정 및 DB에 저장

#### 요구사항
* 회원 목록에서 회원가입한 사용자의 정보를 수정할 수 있어야 한다.
* 비밀번호, 이름, 이메일만 수정할 수 있으며, 사용자 아이디는 수정할 수 없다.
* 비밀번호가 일치하는 경우에만 수정 가능하다.
