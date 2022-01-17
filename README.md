# be-w34-spring-cafe

# step1
- [x] 회원목록 조회 완료
- [x] 회원 추가 완료
- [x] 회원 정보 조회 완료

### 회고
- spring을 인프런 인강을 보며 복붙 코딩을 한 경험이 있습니다. 당시 기억을 바탕으로 어느정도 할 수 있겠다 생각한 것이 참 어리석었고 많이 노력해야할 것 같습니다.
- 코드리뷰 + 다른 동기들의 코드를 보는 과정이 정말 도움이 많이 되는 것 같습니다.

# step2
- [x] 질문하기 완료
- [x] 질문 목록 조회 완료
- [x] 질문 상세 조회 완료

# step3
- [x] h2 database 연결 및 전환
- [x] stream 사용시 orelse(null)보단 exception
- [x] template header footer 분리
- [x] session을 사용하여 로그인 구현
- [ ] domain 객체를 dto로 세분화

### 회고
- 3일차에는 디비연결, 배포를 하고 테스트 코드를 추가하려고 했는데 무엇때문인지 배포까지 완료했으나 배포한 서버에서 디비 연결이 계속 오류나서 하루종일 그 오류만 고치려 한 것 같습니다.(심지어 에러를 못 고쳐서 조금 허무합니다 ㅠㅠ)
- 내일은 테스트 코드 작성 및 예외처리를 진행할 예정입니다.

# step4
- [x] 테스트 코드 작성(mock 사용해보기)
- [ ] 예외처리 진행(advice, handler)
- [x] final 변수 사용할 수 있는 곳 모두 변경

### 공부해볼것
- config파일에서 bean 등록 순서
- 의존성 주입 순서

### 회고
- 테스트는 금방할 줄 알았는데 막상 mvc테스트는 또 새로워서 하루종일 걸렸던 것 같습니다 ㅠㅜ 내일은 예외처리를 하겠습니다.

# step5
- [x] 예외처리 진행(advice, handler) (참고 자료https://catsbi.oopy.io/72475b41-f527-4e64-867c-7cbdc5a04d69)
- [x] 의존성 주입 방법 변경

# step6
- [x] local mysql 사용
- [x] 로그인 하지 않은 사용자는 게시글을 목록만 볼 수 있음(로그인 한 사용자만 게시글의 세부내용을 볼 수 있다.)
- [ ] requestParam validation검증