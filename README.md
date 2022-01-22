# be-w34-spring-cafe


## 초기설정

- [x] 프로젝트 생성
  - [x] java 11 gradle 로 생성하기
  - [x] .gitignore 설정하기
  - [x] 테스트코드 의존성 추가하기
  - [x] template engine 설정하기


## Spring Cafe 1-1

- [x] Spring Layered Architecture 적용
  
  - [x] Domain
    - [x] UserEntity
    - [x] UserDto (1-3에서)
  
  - [x] Service
    - [x] 회원가입
    - [x] 회원 목록
    - [x] 회원 프로필
  
  - [x] Repository
    - [x] UserInterface
    - [x] UserImplMemoryDB
  
  - [x] Controller
    - [x] 회원가입
      - [x] 비밀번호 길이 제한
      - [x] 중복된 ID 확인
      - [x] 고유한 uid 발급
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


- [x] 테스트코드 작성

  - [x] Service
    - [x] 회원가입
      - [x] 비밀번호 6 글자 미만 입력 시 예외 처리
      - [x] 중복된 ID 가입 시도 시 예외 처리
      - [x] 고유한 uid 발급
    - [x] 회원 프로필
      - [x] 없는 프로필 조회 시 404


## Spring Cafe 1-2

- [x] qna 게시글

  - [x] 게시글 쓰기
    - [x] view 만들기 (form)
    - [x] ArticleController 와 그 메소드 구현하기
    - [x] ArrayList 의 inMemoryDB Repository 구현하기
    - [x] Article Domain 구현하기
    - [x] Service 로 Controller 와 Repository 연결하기

  - [x] 게시글 목록 조회
    - [x] view 만들기 (list)
    - [x] 기능 구현하기

  - [x] 게시글 상세 조회
    - [x] view 만들기 (show)
    - [x] articleId 를 ArrayList.size() + 1 로 사용하기
    - [x] 기능 구현하기

  - [x] ArticleDto 사용하기 (1-3에서)

- [x] 회원정보 수정
  - [x] view 만들기 (updateForm)
  - [x] pathVariable 로 controller 메소드 구현하기
  - [x] view 에 기존 데이터 출력하기
  - [x] 기능 구현하기

- [ ] 에러 페이지
  - [x] 에러 페이지 문구 표시
  - [ ] 잘못된 url 접근 시 에러 페이지


- [x] 테스트코드 작성

  - [x] ArticleService
    - [x] 게시글 목록 확인
    - [x] 게시글 작성
    - [x] 없는 게시글 상세 페이지 조회 시 예외 처리

  - [x] UserService
    - [x] 없는 계정의 정보 변경 요청 시 예외 처리 
    - [x] 비밀번호 6 글자 미만 입력 시 예외 처리


- [ ] 리뷰
  - [ ] spring interceptor 를 이용해 의도치 않은 url 요청 처리

  - [x] controllerAdvice 를 이용해 에러와 템플릿 연결
    - [x] GlobalControllerAdvice 클래스 생성

  - [x] 동시성 보장을 위한 자료구조
    - [x] UserRepositoryImplMemoryDB -> ConcurrentHashMap
    - [x] ArticleRepositoryImplMemoryDB -> CopyOnWriteArrayList


## Spring Cafe 1-3

- [x] 리뷰
  - [x] controller 에서 dto 사용
    - [x] UserDto 및 Mapper 만들기
    - [x] ArticleDto 및 Mapper 만들기
    - [x] RequestBody 를 이용해 controller 에서 dto 객체로 파라미터 받기

  - [ ] exception 처리
    - [x] UncheckedException 처리
    - [ ] CustomException 만들기
    - [ ] ExceptionCode Enum 만들기
    - [ ] ResponseEntity 로 예외 처리

- [x] h2 DB 설정
  - [x] 의존성 설정
  - [x] DB Connection 설정
  - [x] Configuration 클래스
  - [x] RepositoryImpl 구현

- [x] 배포하기 (http://root-spring-cafe.ay1.krane.9rum.cc:8080/)
  - [x] Krane 인스턴스 생성
  - [x] Krane 인스턴스에 jar 파일 복붙
  - [x] Krane 인스턴스에서 jar 파일 실행 및 정상 작동 확인
