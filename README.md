# be-w34-spring-cafe

## [1 - 2 단계 요구사항](https://lucas.codesquad.kr/2022-kakao/course/%EC%9B%B9%EB%B0%B1%EC%97%94%EB%93%9C/Kakao-Cafe/%EC%8A%A4%ED%94%84%EB%A7%81-%EC%B9%B4%ED%8E%98-2%EB%8B%A8%EA%B3%84-%EA%B5%AC%ED%98%84)

## Pages yet to be touched
```
/user/login.html
/user/login_failed.html
```

## Static Pages
```
/user/form.html - 회원 가입 페이지
/article/form.html - 게시글 작성 페이지
```

## Dynamic Pages
| URL                            | 기능        |
|--------------------------------|-----------|
| **GET** /users/{username}      | 회원 프로필 조회 |
| **GET** /users                 | 회원 목록 조회  |
| **POST** /users                | 회원 가입     |
| **GET** /articles/{article_id} | 게시글 조회    |
| **GET** /, /articles           | 게시글 목록 조회 |
| **POST** /articles             | 게시글 작성하기  |


## To-Do-List
- [ ] 에러 코드 및 메시지 Enum으로 관리하기, [참고자료](https://samtao.tistory.com/42)
- [ ] 계층화
  - [ ] DAO(Storage - Object)
  - [ ] Repository(Collection logics)
  - [ ] Service(Business Logic)
  - [ ] DTO(View - Entity)
- [ ] Validation, [참고자료](https://kapentaz.github.io/spring/Spring-Boo-Bean-Validation-%EC%A0%9C%EB%8C%80%EB%A1%9C-%EC%95%8C%EA%B3%A0-%EC%93%B0%EC%9E%90/#)
- View 정리, 공통 부분 추출
- 테스트 추가
- Spring 마법 이해하기
- 
