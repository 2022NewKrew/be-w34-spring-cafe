# be-w34-spring-cafe(스프링 카페)

> 스프링을 활용하여 카카오 카페를 구현하는 프로젝트입니다.

<br>

----

## 1. Controller

<br>

### 1.1. ArticleController

> 게시글과 관련된 요청 처리
- `/`
  - `GET`: 메인 페이지(게시글들 표시)
- `/articles`
  - `POST`: 게시글 추가
- `/articles/form`
  - `GET`: 게시글 작성 form
- `/articles/:articleId`
  - `GET`: 게시글 상세보기

<br>

### 1.2. UserController

> 유저와 관련된 사용자 요청을 처리

- `/user`
  - `GET`: 유저 리스트 표시
  - `POST`: 유저 추가
- `/user/form`
  - `GET`: 회원 가입을 위한 form
- `/user/:id`
  - `GET`: id에 해당하는 유저의 프로필

<br>

----

## 2. Repository
- 인터페이스와 클래스로 구현
  - 인터페이스`UserRepository`, 클래스`MemoryUserRepository`

<br>

### 2.1. ArticleRepository
- `.save`: Repository에 게시글을 저장하는 메서드
- `.findByArticleId`: `articleId`로 게시글을 찾는 메서드
- `.findAll`: 모든 게시글을 찾는 메서드

<br>

### 2.2. UserRepository
- `.save`: Repository에 유저를 저장하는 메서드
- `.findById`: `id`로 유저를 찾는 메서드
- `.findByName`: `name`으로 유저를 찾는 메서드
- `.findAll`: 모든 유저를 찾는 메서드

<br>

------

## 3. Service
- 인터페이스와 클래스로 구현
  - 인터페이스`UserService`, 클래스 `UserServiceImpl`

<br>

### 3.1. ArticleService
- `.addArticle`: 게시글을 추가하는 메서드
- `.findArticles`: 모든 게시글을 찾는 메서드
- `.findArticleById`: `articleId`로 게시글을 찾는 메서드

<br>

### 3.2. UserService
- `.addUser`: 유저를 추가하는 메서드
  - `.validDuplicateUser` 메서드를 통해 이미 존재하는 경우 예외처리
- `.findUsers`: 모든 유저를 찾는 메서드
- `.findUserById`: `id`로 유저를 찾는 메서드

<br>

--------

## 4. Data Transfer Object & Entity

<br>

### 4.1 Article
- `ArticleReqDto` : 게시글 생성
- `ArticleResDto` : 게시글 정보 반환
- `Article`
  - `Long articleId`: 시스템에서 지정하는 id
  - `String writer`
  - `String title`
  - `String contents`

<br>

### 4.2 User
- `UserReqDto`: 유저 생성
- `UserUpdateReqDto`: 유저 정보 업데이트
- `UserResDto`: 유저 정보 반환
- `User`
  - `Long id`: 시스템에서 지정하는 id
  - `String userId`
  - `String password`
  - `String name`
  - `String email`

<br>

----------

# Step 1-1 구현사항
- 회원가입, 사용자 목록 기능과 관련된 Controller, VO, Repository, Service 구현
- 구현에 맞는 `html` 파일들 수정
  - user/form.html
  - user/list.html
  - user/profile.html
- mustache 문법에 맞게 header, footer 분리하여 html 중복 최소화

<br>

# Step 1-2 구현사항
- 게시글 생성, 게시글 조회와 관련된 Controller, VO, Repository, Service 구현
- 사용자 정보 수정에 관련된 메서드`UpdateUser`를 `UserService`에 추가
- 구조에 맞게 DTO, Entity 변환 과정 수정

# In Progress
- 전반적인 예외 처리
- Step 1-3
- 메서드 테스트
- html파일 정리