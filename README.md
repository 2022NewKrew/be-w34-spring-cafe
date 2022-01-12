# be-w34-spring-cafe

## 기능 요구사항

### 회원 계정 관리

| URL         | 기능            | Path Variable | Request Body | Response Model | Response View Name    |
|-------------|---------------|---------------|--------------|----------------|-----------------------|
| POST /users | 입력 정보로 회원가입   | X             | UserVo       | X              | (redirect) GET /users |

### 회원 조회

| URL                | 기능        | Path Variable | Request Body | Response Model    | Response View Name |
|--------------------|-----------|---------------|--------------|-------------------|--------------------|
| GET /user/{userId} | 회원 프로필 조회 | userId        | X            | user(name, email) | user/profile       |
| GET /users         | 회원 목록 조회  | X             | X            | users(user list)  | user/list          |

### 글 관리

| URL             | 기능          | Path Variable | Request Body                      | Response Model | Response View Name |
|-----------------|-------------|---------------|-----------------------------------|----------------|--------------------|
| POST /questions | 질문하기 게시글 작성 | X             | ArticleVo(author, title, content) | X              | (redirect) GET /   |

### 글 조회

| URL                   | 기능      | Path Variable | Request Body | Response Model                                    | Response View Name |
|-----------------------|---------|---------------|--------------|---------------------------------------------------|--------------------|
| GET /                 | 글 목록 조회 | X             | X            | articles(article list)                            | qna/form           |
| GET /articles/{index} | 글 상세 조회 | index         | X            | article(index, author, createdAt, title, content) | qna/show           |
