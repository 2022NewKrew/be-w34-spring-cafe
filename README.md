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