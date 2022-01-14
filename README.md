# be-w34-spring-cafe


## 초기설정

- [x] 프로젝트 생성
    - [x] java 11 gradle 로 생성하기
    - [x] .gitignore 설정하기
    - [x] 테스트코드 의존성 추가하기
    - [x] template engine 설정하기


## Spring Cafe 1-1

- [ ] Spring Layered Architecture 적용
  
  - [x] Domain
    - [x] UserEntity
    - [ ] UserDto
  
  - [x] Service
    - [x] 회원가입
    - [x] 회원 목록
    - [x] 회원 프로필
  
  - [x] Repository
    - [x] UserInterface
    - [x] UserImplMemoryDB
  
  - [ ] Controller
    - [ ] 회원가입
      - [x] 비밀번호 길이 제한
      - [x] 중복된 ID 확인
      - [ ] 고유한 uid 발급
    - [x] 회원 목록
    - [x] 회원 프로필
      - [x] 없는 프로필 조회 시 처리
  
  - [x] View
    - [x] template engine 설정
    - [x] 중복 코드 처리 (mustache)
    - [x] 회원가입
    - [x] 회원 목록
    - [x] 회원 프로필
      - [x] 없는 프로필 조회 시 에러 페이지 리디렉트


- [ ] 테스트코드 작성

  - [ ] Service
    - [ ] 회원가입
      - [x] 비밀번호 6 글자 미만 입력 시 예외 처리
      - [x] 중복된 ID 가입 시도 시 예외 처리
      - [ ] 고유한 uid 발급
    - [x] 회원 프로필
      - [x] 없는 프로필 조회 시 404


## Spring Cafe 1-2

- [ ] qna 게시글

  - [x] 게시글 쓰기
    - [x] view 만들기 (form)
    - [x] ArticleController 와 그 메소드 구현하기
    - [x] ArrayList 의 inMemoryDB Repository 구현하기
    - [x] Article Domain 구현하기
    - [x] Service 로 Controller 와 Repository 연결하기

  - [x] 게시글 목록 조회
    - [x] view 만들기 (list)
    - [x] 기능 구현하기

  - [ ] 게시글 상세 조회
    - [ ] view 만들기 (show)
    - [ ] articleId 를 ArrayList.size() + 1 로 사용하기
    - [ ] 기능 구현하기

  - [ ] ArticleDto 사용하기

- [ ] 회원정보 수정
  - [ ] view 만들기 (updateForm)
  - [ ] pathVariable 로 controller 메소드 구현하기
  - [ ] view 에 기존 데이터 출력하기
  - [ ] 기능 구현하기


- [ ] 테스트코드 작성

  - [ ] ArticleService
    - [ ] 

  - [ ] UserService
    - [ ] 
