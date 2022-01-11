# be-w34-spring-cafe



## 각 기능에 따른 url과 메서드 convention

| url                | 기능                             |
| ------------------ | -------------------------------- |
| GET /              | 메인 페이지(글 목록 페이지) 리턴 |
| GET /user          | 유저 목록 페이지 리턴            |
| POST /user/create  | 유저 회원가입                    |
| GET /user/{userId} | 유저 프로필 페이지 리턴          |
| POST /questions    | 게시글 생성                      |
| GET /articles/{id} | 게시글 상세 페이지 리턴          |



## 구현

- Controller
  - UserController
    - 유저 관련 요청 컨트롤러
  - ArticleController
    - 게시글 관련 요청 컨트롤러
- Service
  - UserService
    - 유저 관련 도메인 로직을 호출하는 서비스
  - ArticleService
    - 게시글 관련 도메인 로직을 호출하는 서비스
- Domain
  - User
    - 유저 도메인
  - Article
    - 게시글 도메인
- Repository
  - UserRepository
    - 회원가입, 전체 조회, Id로 조회 메서드를 갖고 있는 인터페이스
    - MemoryUserRepository
      - ArrayList에 유저를 담고 있는 레포지토리
  - ArticleRepository
    - 게시글 등록, 전체 조회, Id로 조회 메서드를 갖고 있는 인터페이스
    - MemoryArticleRepository
      - ArrayList에 게시글을 담고 있는 레포지토리
- Dto
  - UserCreateRequestDto
    - 유저 회원가입 요청 데이터를 담은 Dto
  - UserListResponseDto
    - 유저 목록 데이터를 담은 Dto
  - UserProfileResponseDto
    - 유저 프로필 페이지에 필요한 데이터를 담은 Dto
  - ArticleCreateRequestDto
    - 게시글 등록 요청 데이터를 담은 Dto
  - ArticleListResponseDto
    - 게시글 목록 데이터를 담은 Dto
  - ArticleDetailResponseDto
    - 게시글 상세 페이지에 필요한 데이터를 담은 Dto
- config
  - MvcConfig
    - 로직이 따로 필요없고 정적 페이지만 반환하면 되는 페이지를 등록한 ViewControllerRegistry 를 포함한 Config 파일

