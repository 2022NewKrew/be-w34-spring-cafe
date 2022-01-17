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
