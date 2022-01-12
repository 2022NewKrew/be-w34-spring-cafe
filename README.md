# be-w34-spring-cafe

## 1일차 구현

### 구현 내용

- `handlebars` 추가
- HTML 중복 제거
- 회원가입 기능 구현
- 회원목록 기능 구현

### 코드 리뷰 및 자체 피드백

- `WebMvcConfigurerAdapter`가 `deprecated`된 이유
  - Spring5에서 추상클래스였던 기존의 `WebMvcConfigurerAdapter` 대신 인터페이스 형태의 `WebMvcConfigurer`를 도입함.
  - 추상클래스는 필요하지 않은 메서드도 구현해야 하지만 인터페이스는 그럴 필요가 없음

## 2일차 구현

### 구현 내용

- 게시글 작성 및 목록 기능 구현

### 코드 리뷰 및 자체 피드백

- [ ] `Article` 생성자 사용 시 `@AllArgsConstructor` 사용하여 코드를 줄이고 
`UNDEFINED_ID`를 설정하거나 박스형을 사용해 `null`로 명시적 초기화 권장  
- [ ] `ArticleService::saveArticle`에서 `save()`의 리턴값 그대로 반환
- [ ] 유효성 검사 추가
