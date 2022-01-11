# be-w34-spring-cafe

## 기능 요구사항

### 회원가입


| URL         | 기능            | Path Variable | Request Body | Response Model | Response View Name    |
|-------------|---------------|---------------|--------------|----------------|-----------------------|
| POST /users | 입력 정보로 회원가입   | X             | X            | X              | (redirect) GET /users |


### 회원 목록

| URL        | 기능       | Path Variable | Request Body | Response Model   | Response View Name |
|------------|----------|---------------|--------------|------------------|--------------------|
| GET /users | 회원 목록 조회 | X             | X            | users(user list) | user/list          |

### 회원 프로필

| URL                | 기능        | Path Variable | Request Body | Response Model    | Response View Name |
|--------------------|-----------|---------------|--------------|-------------------|--------------------|
| GET /user/{userId} | 회원 프로필 조회 | userId        | X            | user(name, email) | user/profile       |

