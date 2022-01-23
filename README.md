# be-w34-spring-cafe
## [1-1 ~ 1-3단계](/docs/README_STEP1.md)
## 2-1단계 ([링크](https://lucas.codesquad.kr/2022-kakao/course/%EC%9B%B9%EB%B0%B1%EC%97%94%EB%93%9C/Kakao-Cafe-2/%EC%B9%B4%EC%B9%B4%EC%98%A4-%EC%B9%B4%ED%8E%98-2---4-%EB%8B%A8%EA%B3%84))
### 기능 요구사항
- 로그인이 가능해야 한다.
- 현재 상태가 로그인 상태이면 상단 메뉴가 “로그아웃”, “개인정보 수정”이 나타나야 하며, 로그아웃 상태이면 상단 메뉴가 “로그인”, “회원가입”이 나타나야 한다.
### 상세 구현사항
- 네비게이션 바 내용 수정
  - 로그인 X
    - `로그인`, `회원가입` 버튼 표시
  - 로그인 O
    - 일반 계정: `로그아웃`, `회원정보 수정`, `게시판` 버튼 표시
    - 관리자 계정: 일반 계정과 동일한 버튼들 + `회원목록` 버튼 표시
- 직접 정의한 Annotation과 AOP를 통해 로그인이 필요한 부분 체크
  - 주소로 직접 접근했을 때를 방지하기 위함
  - 접근하지 못하도록 AOP 처리 로직에서 예외를 던짐
  - 현재 막은 행동들
    - 로그인하지 않은 상태로 회원정보 수정, 게시판 관련 페이지 접근
    - 관리자 계정으로 로그인하지 않은 상태로 회원목록 및 회원 상세정보 페이지 접근
- 로그인 구현
  - 이미 로그인에 필요한 정보 (회원 ID, 비밀번호)로 사용자를 조회하는 로직을 구현해놓은 상태였으므로 해당 내용 활용
  - 아직 Spring Security와 관련된 내용은 적용하지 않음
  - 로그인을 하게 되면 로그인한 계정의 ID와 관리자인지에 대한 정보를 세션에 저장
### 실행 화면
<details>
    <summary>펼치기</summary>
    <h4>< 로그인 성공 ></h4>
    <img src="img/step2_1_1_login_o.gif" alt="step2_1_1_login_o">
    <h4>< 로그인 실패 ></h4>
    <img src="img/step2_1_2_login_x.gif" alt="step2_1_2_login_x">
    <h4>< 일반 계정으로 로그인 후 확인 ></h4>
    <img src="img/step2_1_3_login_common.gif" alt="step2_1_3_login_common">
    <h4>< 관리자 계정으로 로그인 후 확인 ></h4>
    <img src="img/step2_1_4_login_admin.gif" alt="step2_1_4_login_admin">
    <h4>< 로그인하지 않았을 때 접근 방지 ></h4>
    <img src="img/step2_1_5_ban.gif" alt="step2_1_5_ban">
</details>

## 2-2단계 ([링크](https://lucas.codesquad.kr/2022-kakao/course/%EC%9B%B9%EB%B0%B1%EC%97%94%EB%93%9C/Kakao-Cafe-2/%EC%8A%A4%ED%94%84%EB%A7%81-%EC%B9%B4%ED%8E%98-2---2-%EB%8B%A8%EA%B3%84))
### 기능 요구사항
- 게시글 요구 사항
  - 로그인하지 않은 사용자는 게시글의 목록만 볼 수 있다.
  - 로그인한 사용자만 게시글의 세부내용을 볼 수 있다.
  - 로그인한 사용자만 게시글을 작성할 수 있다.
  - 로그인한 사용자는 자신의 글을 수정 및 삭제할 수 있다.
- DB 요구 사항 (예정)
  - 데이터베이스를 MySQL로 변경한다.
  - 서버에 MySQL을 설치하는 경험을 해 본다.
  - 프로젝트 구성은 필요에 따라 로컬 설치, 도커를 통한 설치, 서버에 설치 중 편한 방법을 선택해서 연동한다.
### 상세 구현사항
- 네비게이션 바 내용 수정
  - 로그인을 하지 않더라도 `게시판` 버튼 표시
  - 로그인을 했을 때, 로그인한 계정의 정보 (ID, 이름)를 표시
- 직접 정의한 Annotation (`MineCheck`)과 AOP를 통해 게시글에서 로그인이 필요한 부분 체크
  - `BoardService` 인터페이스에 현재 게시글 또는 댓글이 받은 ID로 작성된 것인지 확인해주는 메소드들 추가 후 구현
  - Annotation 및 표현식으로 구분된 메소드에 AOP를 적용하며 게시글인지 댓글인지 확인한 후 파라미터를 가져와서 확인
### 실행 화면
<details>
    <summary>펼치기</summary>
    <h4>< 게시글 접근 ></h4>
    <img src="img/step2_2_1_read_article.gif" alt="step2_2_1_read_article">
    <h4>< 게시글 추가 후 수정, 삭제 ></h4>
    <img src="img/step2_2_2_modify_delete_article.gif" alt="step2_2_2_modify_delete_article">
    <h4>< 게시글 수정 및 삭제 실패 ></h4>
    <img src="img/step2_2_3_modify_delete_article_fail.gif" alt="step2_2_3_modify_delete_article_fail">
    <h4>< 댓글 추가 후 삭제 ></h4>
    <img src="img/step2_2_4_add_delete_comment.gif" alt="step2_2_4_add_delete_comment">
    <h4>< 댓글 삭제 실패 ></h4>
    <img src="img/step2_2_5_delete_comment_fail.gif" alt="step2_2_5_delete_comment_fail">
</details>

## 2-3단계 ([링크](https://lucas.codesquad.kr/2022-kakao/course/%EC%9B%B9%EB%B0%B1%EC%97%94%EB%93%9C/Kakao-Cafe-2/4%EB%8B%A8%EA%B3%84--User%EC%99%80-Question-%EA%B4%80%EA%B3%84-%EC%84%A4%EC%A0%95))
### 기능 요구사항
- 댓글 기능 구현
  - 로그인한 사용자는 게시글 상세보기 화면에서 댓글들을 볼 수 있다.
  - 로그인한 사용자는 댓글을 추가할 수 있다.
  - 자신이 쓴 댓글을 삭제할 수 있다.
- 배포 연습 (예정)
  - 도커를 설치하고 도커를 사용해서 배포 연습을 한다.
### 피드백 반영
- 패키지 구성 변경
  - 기존에 DTO와 도메인 패키지가 `model` 패키지 안에 함께 존재했으며 이를 분리
  - 현재 구현된 서비스 객체들은 단순히 도메인 객체들을 사용하여 흐름을 제어하는 느낌이므로 Application Service에 가까움
  - 위 내용을 반영하여 구성을 다음과 같이 변경
  ```
    ├── aop
    ├── application
    │   ├── dto
    │   └── service
    ├── config
    ├── controller
    ├── model
    │   ├── domain
    │   └── repository
    └── util
    ├── annotation
    └── exception
  ```
### 상세 구현사항
- MySQL로 DB 변경
  - Krane 인스턴스에 MySQL 설치 후 외부에서 접근이 가능하도록 설정
  - DB 구성 후 사용
- Soft Delete 구현
  - `ARTICLES`와 `COMMENTS` 테이블에 `IS_DELETE`라는 `BOOLEAN`형의 속성 추가
  - 삭제 여부에 따라 가져오는 레코드들을 다르게 하기 위해 쿼리문 수정
- 게시글 삭제 로직 변경
  - 현재 로그인돼있는 계정이 작성한 글이면 바로 삭제가 가능했던 것을 다른 계정이 작성한 댓글이 존재할 경우 삭제 불가능하도록 변경
### 실행 화면
<details>
    <summary>펼치기</summary>
    <h4>< 다른 계정의 댓글로 인한 삭제 실패 ></h4>
    <img src="img/step2_3_1_delete_article_failed.gif" alt="step2_3_1_delete_article_failed">
    <h4>< 댓글 삭제 ></h4>
    <img src="img/step2_3_2_delete_comment.gif" alt="step2_3_2_delete_comment">
    <h4>< 게시글 삭제 ></h4>
    <img src="img/step2_3_3_delete_article.gif" alt="step2_3_3_delete_article">
    <h4>< 게시글 및 댓글 작성 후 삭제 ></h4>
    <img src="img/step2_3_4_make_and_delete_all.gif" alt="step2_3_4_make_and_delete_all">
    <h4>< Soft Delete 확인 ></h4>
    <img src="img/step2_3_5_soft_delete.gif" alt="step2_3_5_soft_delete">
</details>

## 2-4단계 ([링크](https://lucas.codesquad.kr/2022-kakao/course/%EC%9B%B9%EB%B0%B1%EC%97%94%EB%93%9C/Kakao-Cafe-2/5%EB%8B%A8%EA%B3%84---%EB%8C%93%EA%B8%80-%EA%B5%AC%ED%98%84))
### 기능 요구사항
- RestController와 Web API로 댓글 구현하기
  - 기존에 댓글을 구현한 방식은 매 번 새로고침을 통해 추가, 삭제
  - 비동기 통신을 통해 댓글 추가 및 삭제 구현
### 상세 구현사항
- 댓글 부분에서 비동기 통신을 하기 위해 `axios` 활용 및 JavaScript 구현
- `BoardController`에서 댓글 관련 메소드에 `@ResponseBody` 어노테이션을 붙여 부분적으로 REST API를 구현
- 댓글 부분에서만 우선적으로 REST API와 비동기 통신을 구현하기 때문에 예외를 따로 정의하고 Advice에서 따로 처리
### 실행 화면
<details>
    <summary>펼치기</summary>
    <h4>< 댓글 추가 및 삭제, 뒤로가기 앞으로 가기를 통해 비동기 통신 확인 ></h4>
    <img src="img/step2_4_1_comment_async.gif" alt="step2_4_1_comment_async">
    <h4>< 댓글 삭제 실패 ></h4>
    <img src="img/step2_4_2_delete_failed_alert.gif" alt="step2_4_2_delete_failed_alert">
    <h4>< 다른 아이디로 들어와 댓글 삭제 성공 ></h4>
    <img src="img/step2_4_3_delete_success.gif" alt="step2_4_3_delete_success">
</details>

## 2-5단계 ([링크](https://lucas.codesquad.kr/2022-kakao/course/%EC%9B%B9%EB%B0%B1%EC%97%94%EB%93%9C/Kakao-Cafe-2/6%EB%8B%A8%EA%B3%84---%ED%8E%98%EC%9D%B4%EC%A7%95-%EA%B5%AC%ED%98%84))
### 피드백 반영
- Repository 관련 수정
  - 각 도메인 별로 Repository 클래스가 존재해야 하므로 `BoardRepository`를 `ArticleRepository`와 `CommentRepository`로 분할
  - `BoardService`에서 두 Repository 객체를 주입받아서 사용
- API 디자인 수정
  - UserController
    - |요청 주소|기능|
      |-------|---|
      |`GET /user/register`|회원가입 Form|
      |`GET /user/login`|로그인 Form|
      |`GET /user/logout`|로그아웃|
      |`GET /user/list`|회원목록|
      |`GET /user/{userId}`|해당 회원 정보 열람|
      |`GET /user/{userId}/edit`|해당 회원 정보 수정 Form|
      |`POST /user/register`|회원가입|
      |`POST /user/login`|로그인|
      |`PUT /user/{userId}/edit`|해당 회원 정보 수정|
  - BoardController
    - |요청 주소|기능|
      |-------|---|
      |`GET /board/articles`|게시글 목록|
      |`GET /articles/pagination`|페이징을 위한 정보|
      |`GET /board/articles/form`|게시글 작성 Form|
      |`GET /board/articles/{articleId}`|해당 게시글 열람|
      |`GET /board/articles/{articleId}/edit`|해당 게시글 수정 Form|
      |`POST /board/articles`|게시글 작성|
      |`POST /board/articles/{articleId}/comments`|해당 게시글에 댓글 작성|
      |`PUT /board/articles/{articleId}`|해당 게시글 수정|
      |`DELETE /board/article/{articleId}`|해당 게시글 삭제|
      |`DELETE /board/articles/{articleId}/comments/{commentId}`|해당 게시글의 해당 댓글 삭제|
### 기능 & 프로그래밍 요구사항
- 질문을 한 페이지에 15개씩 가져오도록 구현한다.
- 질문 목록은 생성일 기준 내림차순으로 구현한다.
- 시작 번호, 끝 번호 등을 구하고, 동적으로 html을 생성하는 로직 구현을 TDD로 구현해야 한다.
### 상세 구현사항
- 테스트 코드 먼저 작성하여 확인
  - `ArticleRepositoryJdbcImplTest`: 원하는 개수 만큼의 게시글과 전체 게시글 수를 정확하게 가져오는지 확인
  - `BoardServiceImplTest`: 현재 페이지 및 보여주려는 게시글의 수에 맞게 페이지네이션 정보를 가져오는지 확인
- `ArticleRepository`
  - 원하는 범위와 수량의 게시글들을 가져오는 `findArticlesByStartAndWantedCountPerPage` 메소드 정의 및 구현
- `BoardService`
  - 현재 페이지에 해당하는 게시글들을 가져오는 `findArticlesByCurrentPageAndCountPerPage` 메소드 정의 및 구현
- `BoardController`
  - `GET /board/articles` 요청을 받는 메소드에 요청 파라미터로 현재 페이지와 한 페이지에 보여줄 게시글 수를 입력받도록 수정
  - `GET /articles/pagination` 요청을 받는 메소드를 추가하여 페이징 정보들을 담아서 전달
- `PaginationDto`를 정의하여 view에 전달할 페이징 정보들을 모두 담아서 전달할 때 사용
- `/board/list.html`에 페이지 정보를 담을 `div` 태그 추가 및 `list.js` 작성
### 실행 화면
<details>
    <summary>펼치기</summary>
    <h4>< 페이징 확인 ></h4>
    <img src="img/step2_5_1_paging.gif" alt="step2_5_1_paging">
</details>