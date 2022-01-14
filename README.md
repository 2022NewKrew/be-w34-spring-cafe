# be-w34-spring-cafe

### Install
```
$sudo chmod 750 gradlew
$./gradlew build
$./gradlew bootRun

## see localhost:8080
## memory h2 db localhost:8080/h2-console
## jdbc url : jdbc:h2:mem:kakaodb
```

### Step1 구현 사항
- 회원가입
- 회원목록 조회
- mustache + bootstrap 템플릿은 따로 가져와 커스텀했습니다..
- spring jdbc 연결 - h2

### Step2, 3, 구현 사항
- 로그인(Session + Aspect) 및 로그인에 따른 UI 처리
- 게시판 목록조회, 글 읽기, 새 글 작성

### Todo - 더 해야 할 것.
- [ ] GlobalExceptionHandler - ExceptionHandler 처리 및 UI 대응개발
- [ ] 로깅 - Aspectj 공통 IN/OUT 처리
- [ ] Validation Check - validation 및 Exceptionhandler로 처리
- [ ] Password encryption - Bcrypt?
- [ ] 로그인 - Session + cookie 로
- [ ] 테스트코드 작성
