# be-w34-spring-cafe

DDD하려 했는데 잘 안돼서 TDD라도 열심히 했습니다.ㅜ

프론트 부분이 잘 안돌아가서(백단부터 하고 후에 처리할 예정입니다.) 테스트 코드랑 swagger로 대체했습니다.

(서버 실행 시키고 아래 주소로 들어가면 api 볼 수 있습니다.)
http://localhost:8080/swagger-ui.html#/ 


예외처리는 GlobalExceptionHandler에 모든 컨트롤러에서 사용할만한 validation에 관해 처리해두었고,
비즈니스 로직에서 발생하는 예외는 custom exception 만들어서 해당 컨트롤러에서 처리했습니다. 
