# be-w34-spring-cafe

[참고자료](https://lucas.codesquad.kr/2022-kakao/course/%EC%9B%B9%EB%B0%B1%EC%97%94%EB%93%9C/Kakao-Cafe)

### 📌 회원가입 기능 구현

![img.png](img/img.png)

### 📌 회원목록 기능 구현

![img_1.png](img/img_1.png)

### 📌 회원 프로필 조회

![img_2.png](img/img_2.png)

### 📌 글쓰기 및 글 목록 기능 구현

(그림에서 `질문`을 `게시글`로 변경)

![img.png](img/img3.png)

### 📌 URL Convention

| url                  | 기능                  |
|----------------------|---------------------|
| `GET` /users         | List all users      |
| `POST` /users        | Create a new user   |
| `GET` /users/form    | Get a register form |
| `GET` /users/:userId | Get a user profile  |
| `GET` /              | List all posts      |
| `POST` /posts        | Create a new post   |
| `GET` /posts/form    | Get a post form     |
| `GET` /posts/:postId | Get a post detail   |
