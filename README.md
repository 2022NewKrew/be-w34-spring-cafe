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
