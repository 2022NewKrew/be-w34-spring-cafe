# be-w34-spring-cafe

## 기능 요구사항

### 회원 계정 관리

| URL         | 기능            | Path Variable | Request Body | Response Model | Response View Name    |
|-------------|---------------|---------------|--------------|----------------|-----------------------|
| POST /users | 입력 정보로 회원가입   | X             | UserVo       | X              | (redirect) GET /users |

#### Exception case

- 이미 존재하는 ID
- Null 필드 값(userId, password, name, email)

### 회원 조회

| URL                | 기능        | Path Variable | Request Body | Response Model    | Response View Name |
|--------------------|-----------|---------------|--------------|-------------------|--------------------|
| GET /user/{userId} | 회원 프로필 조회 | userId        | X            | user(name, email) | user/profile       |
| GET /users         | 회원 목록 조회  | X             | X            | users(user list)  | user/list          |

#### Exception case

- 존재하지 않는 id로 프로필 조회 요청 

### 회원 정보 수정

| URL                    | 기능            | Path Variable | Request Body  | Response Model   | Response View Name |
|------------------------|---------------|---------------|---------------|------------------|--------------------|
| GET /user/{id}/form    | 회원 정보 수정 화면   | userId        | X             |                  | user/form          |
| POST /user/{id}/update | 입력한 정보로 회원 수정 | userId        | UserUpdateDto | users(user list) | user/list          |

#### Exception case

- 존재하지 않는 id로 요청
- 기존의 비밀번호와 일치하지 않는 비밀번호
- Null 필드 값(userId, password, name, email)

### 글 관리

| URL             | 기능          | Path Variable | Request Body                      | Response Model | Response View Name |
|-----------------|-------------|---------------|-----------------------------------|----------------|--------------------|
| POST /questions | 질문하기 게시글 작성 | X             | ArticleVo(author, title, content) | X              | (redirect) GET /   |

- Null 필드 값(writer, title, contents)

### 글 조회

| URL                   | 기능      | Path Variable | Request Body | Response Model                                    | Response View Name |
|-----------------------|---------|---------------|--------------|---------------------------------------------------|--------------------|
| GET /                 | 글 목록 조회 | X             | X            | articles(article list)                            | qna/form           |
| GET /articles/{index} | 글 상세 조회 | index         | X            | article(index, author, createdAt, title, content) | qna/show           |

- 존재하지 않는 글 번호로 조회