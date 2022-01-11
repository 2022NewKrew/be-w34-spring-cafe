# be-w34-spring-cafe(스프링 카페)

> 스프링을 활용하여 카카오 카페를 구현하는 프로젝트입니다.

<br>

----

## 1. Controller

<br>

### 1.1. HomeController

> 기본 요청 처리

<br>

### 1.2. UserController

> 유저와 관련된 사용자 요청을 처리

- `/user`
  - `GET`: 유저 리스트 표시
  - `POST`: 유저 추가
- `/user/form`
  - `GET`: 회원 가입을 위한 form
- `/user/{id}`
  - `GET`: id에 해당하는 유저의 프로필

<br>

----

## 2. Repository
- 인터페이스와 클래스로 구현
  - 인터페이스`UserRepository`, 클래스`MemoryUserRepository`

<br>

### 2.1. UserRepository
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

### 3.1. UserService
- `.addUser`: 유저를 추가하는 메서드
  - `.validDuplicateUser` 메서드를 통해 이미 존재하는 경우 예외처리
- `.findUsers`: 모든 유저를 찾는 메서드
- `.findUserById`: `id`로 유저를 찾는 메서드

<br>

--------

## 4. Value Object

<br>

### 4.1 User
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

# In Progress
- ~~UserDTO 생성 예정~~
- 페이지에서 나타나는 내용들 수정