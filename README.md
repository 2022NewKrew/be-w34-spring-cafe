# be-w34-spring-cafe
## 1-1단계 ([링크](https://lucas.codesquad.kr/2022-kakao/course/%EC%9B%B9%EB%B0%B1%EC%97%94%EB%93%9C/Kakao-Cafe/%EC%B9%B4%ED%8E%98-%EA%B5%AC%ED%98%84-1%EB%8B%A8%EA%B3%84))
### 기능 요구사항
- 회원가입, 사용자 목록 기능 구현
- 웹페이지 디자인은 [해당 링크](https://www.figma.com/file/nwhBasptomWJCAMkElxp74/%EC%9E%90%EB%B0%94%EB%B0%B1%EC%97%94%EB%93%9C%EA%B5%90%EC%9C%A1%EC%9A%A9%EC%9B%B9%ED%8E%98%EC%9D%B4%EC%A7%80?node-id=0%3A1) 참고
- 회원가입 기능 구현
  ![회원가입 기능 구현](https://s3.ap-northeast-2.amazonaws.com/lucas-image.codesquad.kr/1641743868263user_form.PNG)
- 회원목록 기능 구현
  ![회원목록 기능 구현](https://s3.ap-northeast-2.amazonaws.com/lucas-image.codesquad.kr/1641743976051user_list.PNG)
- 회원 프로필 조회
  ![회원 프로필 조회](https://s3.ap-northeast-2.amazonaws.com/lucas-image.codesquad.kr/1641744019487user_profile.PNG)
### 상세 구현사항
- MVC 패턴으로 패키지 분리
    - `advice`: 전역적, 공통으로 처리할 로직들 (로깅, 예외처리 등)
    - `controller`: 컨트롤러
    - `model`
        - `domain`: DTO
        - `repository`: DAO
        - `service`: 핵심 로직
    - `util`
        - `exception`: 직접 정의한 예외들
- 현재 DTO는 `User`만 존재
  - Lombok을 활용하여 Getter 및 생성자 등을 구현
- `repository`와 `service`는 확장성, 재사용성을 위해 인터페이스를 정의하여 사용
- 현재 단계에서는 `repository`는 메모리에 저장하도록 구현
    - `Map<String, User>` 활용, 여기`서 키 값은 사용자 ID
- HTML, CSS를 디자인 참고 자료를 활용하여 제작
- Template Engine (Mustache)을 활용하여 동적 페이지 구성
- 중복된 회원이나 없는 회원을 찾을 때의 예외처리 및 오류 화면 출력
### 실행 화면
#### < 첫 회원가입 >
![step1_1_first_register](img/step1_1_first_register.gif)
#### < 두 번째 회원가입 >
![step1_1_second_register](img/step1_1_second_register.gif)
#### < 중복된 회원가입 >
![step1_1_second_register](img/step1_1_duplicated.gif)
