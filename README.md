# be-w34-spring-cafe

## 뷰 변경
- [ ] 뷰 슬랙에 올라온 걸로 변경
## 글쓰기 및 글 목록 기능 구현
- [ ] 글쓰기 요구사항
    - [ ] 게시글 기능 구현 담당할 ArticleController 생성
    - [ ] /qna/form에서 /questions로 post요청
    - [ ] 질문 저장후 "/"로 redirect
    - [ ] 게시글 관련 Service, Repository 생성
    - [ ] Controller - Service간 Dto 사용
- [ ] 글 목록 요구사항
    - [ ] 전체 질문 목록 확인
- [ ] 게시글 상세보기
  - [ ] 게시글 목록 (/qna/list)의 제목을 클릭하면 게시글 상세 페이지에 접속
  - [ ] 상세 페이지 접근 url은 "/articles/{index}" (index == 1번부터)
  - [ ] 게시글 객체에 id PK를 추가하여 사용
  - [ ] 조회한 게시글 데이터를 /qna/show에 보냄
  - [ ] /qna/show에서 확인

## (선택미션) 회원정보 수정
- [ ] 비밀번호, 이름, 이메일만 수정 가능.
- [ ] 비밀번호가 일치하는 경우에만 수정 

### 회원정보 수정화면 기능 구현
- [ ] /users에서 /users/{id}/form으로 개인정보 수정화면 get요청
- [ ] /users/{id}/form에서 수정할 사용자 정보 전달(변수 이름은 user)
- [ ] /user/updateForm 개인정보 수정 화면 출력

### 회원정보 수정 
- [ ] /user/updateFrom에서 /users/{id}/update 로 개인정보 수정
- [ ] /users로 redirect하여 적용 되었는지 조회