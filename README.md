# be-w34-spring-cafe

## URL 목록

### 공통

- index 페이지
  - GET /

### 회원(User)

- 회원가입 페이지
  - GET /user/form
- 목록 가져오기
  - GET /users
- 회원가입
  - POST /users
- 프로필 보기
  - GET /users/{userId} 

### 게시글(Article)

- 게시글 목록 보기
  - GET /
- 글쓰기 페이지
  - GET /article/form
- 게시글 작성하기
  - POST /articles
- 게시글 상세 보기
  - GET /articles/{index}
