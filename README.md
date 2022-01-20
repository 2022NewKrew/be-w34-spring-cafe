# be-w34-spring-cafe

| URL                         | 기능                                    |
|:----------------------------|:--------------------------------------|
| GET /                       | 모든 article 불러오기(홈)                    |
| GET /user/form              | 회원가입 form 불러오기                        |
| POST /user/login            | 로그인 form 불러오기                       |
| GET /user                   | 모든 회원 불러오기                          |
| POST /user                  | 회원 가입 |
| GET /user/{id}              | 회원 정보 불러오기                  |
| PUT /user/{id}/             | 회원 정보 수정 |
| GET /user/{id}/form         | 회원 정보 수정 form 불러오기 |
| POST /login                 | 로그인 후 session 추가                      |
| POST /logout                | 현재 세션에 로그인되어 있는 유저 로그아웃               |
| GET /articles/form          | 게시글 작성 form 불러오기                |
| POST /articles              | 새로운 article 게시(DB에 삽입)                |
| GET /articles/{id}          | 특정 article 정보 가져오기                    |
| PUT /articles/{id}          | 특정 article 수정                    |
| DELETE /articles/{id}       | 특정 article 삭제                    |
| GET /articles/{id}/edit     | 특정 article 편집 form 불러오기                    |
| POST /articles/{articleIdx} | 특정 게시글에 댓글 달기 |
| PUT /articles/{articleIdx}/comments/{commentIdx} | 특정 게시글에 단 댓글 수정 |
| DELETE /articles/{articleIdx}/comments/{commentIdx} | 특정 게시글에 단 댓글 삭제 |

## 1주차

### 1일차 구현

#### 구현 내용

- `handlebars` 추가
- HTML 중복 제거
- 회원가입 기능 구현
- 회원목록 기능 구현

#### 코드 리뷰 및 자체 피드백

- `WebMvcConfigurerAdapter`가 `deprecated`된 이유
  - Spring5에서 추상클래스였던 기존의 `WebMvcConfigurerAdapter` 대신 인터페이스 형태의 `WebMvcConfigurer`를 도입함.
  - 추상클래스는 필요하지 않은 메서드도 구현해야 하지만 인터페이스는 그럴 필요가 없음

### 2일차 구현

#### 구현 내용

- 게시글 작성 및 목록 기능 구현

#### 코드 리뷰 및 자체 피드백

- [x] `Article` 생성자 사용 시 `@AllArgsConstructor` 사용하여 코드를 줄이고 
`UNDEFINED_ID`를 설정하거나 박스형을 사용해 `null`로 명시적 초기화 권장  
- [x] `ArticleService::saveArticle`에서 `save()`의 리턴값 그대로 반환
- [ ] 유효성 검사 추가

### 3일차 구현

#### 구현 내용

- H2 DB 적용
- 회원정보 수정 기능 추가

#### 코드 리뷰 및 자체 피드백

- [x] Reference Type 값 비교시 `equals()` 사용
- [x] DTO <-> 도메인 Mapping Layer 생성하여 코드 간소화

## 2주차

### 1일차 구현

#### 구현 내용

- 로그인 기능 구현
- Logger 추가
- 테스트 데이터 추가

#### 코드 리뷰 및 자체 피드백

- [x] 테스트 코드 작성 시 `@RequiredArgsConstructor` 이용한 DI 사용 지양
> 참고 : https://pinokio0702.tistory.com/189?category=414017
- [x] 로그아웃은 GET인가? POST인가?
> 참고 : https://stackoverflow.com/questions/3521290/logout-get-or-post
- [ ] 패스워드 암호화 (DB, 백엔드)

### 2일차 구현

- 게시글 작성 기능 수정
- 게시글 수정 기능 추가
- 게시글 삭제 기능 추가

#### 코드 리뷰 및 자체 피드백

- [ ] 요청한 값이 DB에 존재하지 않을 경우의 예외 처리
- [ ] `<br>` 태그가 수정 화면에서 노출되는 문제 해결 필요

### 3일차 구현

- MySQL 프로필 추가
- 댓글 기능 추가

#### 코드 리뷰 및 자체 피드백

- [x] 응답 결과를 반환할 때 Entity가 아닌 DTO를 반환
- [ ] Entity에서는 외래키 ID를 필드로 갖는 것이 유리  

### 4일차 구현
