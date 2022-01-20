# be-w34-spring-cafe


## 설명
```
간단한 웹서비스 목적입니다.
html을 동적으로 보여줍니다.
데이터 저장은 DB가 아닌 현재는 메모리 저장으로 구현되어있습니다.
-> Jdbc와 H2를 이용하여 인메모리 디비 저장으로 변경되었습니다.
-> MYSQL로 변경하였습니다.
```
## 구조
```
users
- User
- UserService
- UserController
- UserDto
- UserRepostiory
    - MemoryUserRepository
    - JdbcUserRepository
    
articles
- Article
- AritcleService
- ArticleController
- ArticleDto
- ArticleRepository
    - MemoryArticleRepository
    - JdbcArticleRepository
```

## URL
```
회원가입 : /users/from
사용자 목록 : /users
```

## 기능구현
```
회원가입
사용자 목록 기능 구현
게시글 작성 구현(세션활용)
게시글 리스트
게시글 상세
회원 로그인(세션활용)    
댓글 달기(진행중)
댓글 지우기(진행중)
글에 달린 댓글들(진행중)
```