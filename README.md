# be-w34-spring-cafe

DDD하려 했는데 잘 안돼서 TDD라도 열심히 했습니다.ㅜ

프론트 부분이 잘 안돌아가서(백단부터 하고 후에 처리할 예정입니다.) 테스트 코드랑 swagger로 대체했습니다.

(서버 실행 시키고 아래 주소로 들어가면 api 볼 수 있습니다.)
http://localhost:8080/swagger-ui.html#/ 


예외처리는 GlobalExceptionHandler에 모든 컨트롤러에서 사용할만한 validation에 관해 처리해두었고,
비즈니스 로직에서 발생하는 예외는 custom exception 만들어서 해당 컨트롤러에서 처리했습니다. 

---
### 1-1 피드백 반영 내용
- [x] build.gradle 주석 지우기 
- [x] accountDto - toDto() 메서드 없애기(도메인이 dto를 알고 있다는 것이 부자연스러움, 관련 도메인을 사용하는 Dto가 늘어나면 dto로 변환하는 메서드가 너무 많아질듯) => mapper로 처리
- [x] AccountRepositoryImpl - 여러 개의 요청이 들어왔을 때를 대비하여 List 타입을 동기화가 가능한 타입으로 변경
- [x] 에러에 대한 처리 다른 클래스로 빼기
- [x] 스트림을 이용한 Collection 생성 
