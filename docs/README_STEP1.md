## 1-1단계 ([링크](https://lucas.codesquad.kr/2022-kakao/course/%EC%9B%B9%EB%B0%B1%EC%97%94%EB%93%9C/Kakao-Cafe/%EC%B9%B4%ED%8E%98-%EA%B5%AC%ED%98%84-1%EB%8B%A8%EA%B3%84))
### 기능 요구사항
- 회원가입, 사용자 목록 기능 구현
- 웹페이지 디자인은 [해당 링크](https://www.figma.com/file/nwhBasptomWJCAMkElxp74/%EC%9E%90%EB%B0%94%EB%B0%B1%EC%97%94%EB%93%9C%EA%B5%90%EC%9C%A1%EC%9A%A9%EC%9B%B9%ED%8E%98%EC%9D%B4%EC%A7%80?node-id=0%3A1) 참고
- 회원가입 기능 구현
- 회원목록 기능 구현
- 회원 프로필 조회
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
    - `Map<String, User>` 활용, 여기서 키 값은 사용자 ID
- HTML, CSS를 디자인 참고 자료를 활용하여 제작
- Template Engine (Mustache)을 활용하여 동적 페이지 구성
- 중복된 회원이나 없는 회원을 찾을 때의 예외처리 및 오류 화면 출력
### 실행 화면
<details>
    <summary>펼치기</summary>
    <h4>< 첫 회원가입 ></h4>
    <img src="img/step1_1_1_first_register.gif" alt="step1_1_first_register">
    <h4>< 두 번째 회원가입 ></h4>
    <img src="img/step1_1_2_second_register.gif" alt="step1_1_first_register">
    <h4>< 중복된 회원가입 ></h4>
    <img src="img/step1_1_3_duplicated.gif" alt="step1_1_first_register">
</details>

## 1-2단계 ([링크](https://lucas.codesquad.kr/2022-kakao/course/%EC%9B%B9%EB%B0%B1%EC%97%94%EB%93%9C/Kakao-Cafe/%EC%8A%A4%ED%94%84%EB%A7%81-%EC%B9%B4%ED%8E%98-2%EB%8B%A8%EA%B3%84-%EA%B5%AC%ED%98%84))
### 기능 요구사항
- 글쓰기 및 글 목록 기능 구현
- 글쓰기 요구사항
    - 사용자는 게시글을 작성할 수 있어야 함
- 글목록 요구사항
    - 모든 사용자는 게시글을 볼 수 있어야 함
- 회원 프로필 조회
    - 모든 사용자는 게시글 상세 내용을 볼 수 있어야 함
- 회원정보 수정
    - 회원 목록에서 회원가입한 사용자의 정보를 수정할 수 있어야 함.
    - 비밀번호, 이름, 이메일만 수정할 수 있으며, 사용자 아이디는 수정할 수 없음
    - 비밀번호가 일치하는 경우에만 수정 가능
### 피드백 반영
- 도메인과 DTO의 분리
    - DTO는 Client, Controller, Service 사이에서 데이터를 전달하는 목적으로 활용
    - 도메인은 Service, DAO, Repository 사이에서 데이터를 사용하고 저장하는 것을 목적으로 활용
    - Service 내에서 도메인과 DTO의 변환이 일어나며 ModelMapper를 활용
- DAO를 위한 인터페이스에서 너무 DB스러운 메소드명 변경
- 잘못 활용되고 있는 Optional 부분 변경
### 상세 구현사항
- 패키지 재구성
    - `aop`: AOP를 적용할 로직들 (Logging)
    - `config`: Bean 등록 및 Spring 설정
    - `controller`: 컨트롤러, 전역적인 예외 처리
    - `model`
        - `domain`: Service ↔︎ DAO ↔︎ Repository 사이에서 데이터를 사용하는 도메인 클래스들
        - `dto`: Client ↔︎ Controller ↔︎ Service 사이에서 데이터를 전달 목적으로 사용하는 클래스들
        - `repository`: 저장소와 관련된 클래스들
        - `service`: 핵심 서비스 로직들과 관련된 클래스들
    - `util`
        - `exception`: 직접 정의한 예외들
- 글쓰기 및 글 조회 기능 추가
- 글 조회 화면에서 댓글 작성 기능 구현
- 글 삭제, 수정, 댓글 삭제 기능 구현
- 회원정보 수정 구현
    - 기존 비밀번호와 일치할 때만 수정 가능
- 전역적으로 보여주던 오류를 오류가 발생한 페이지에 오류 메시지와 함께 다시 전달하여 보여주도록 변경
### 실행 화면
<details>
    <summary>펼치기</summary>
    <h4>< 첫 회원가입 ></h4>
    <img src="img/step1_2_1_first_register.gif" alt="step1_2_1_first_register">
    <h4>< 중복 회원가입 ></h4>
    <img src="img/step1_2_2_duplicated_register.gif" alt="step1_2_2_duplicated_register">
    <h4>< 회원정보 수정 실패 ></h4>
    <img src="img/step1_2_3_modify_user_fail.gif" alt="step1_2_3_modify_user_fail">
    <h4>< 글쓰기 및 조회 ></h4>
    <img src="img/step1_2_4_write_article.gif" alt="step1_2_4_write_article">
    <h4>< 댓글 작성 및 삭제 ></h4>
    <img src="img/step1_2_5_write_comment.gif" alt="step1_2_5_write_comment">
    <h4>< 글 수정 및 삭제 ></h4>
    <img src="img/step1_2_6_modify_article.gif" alt="step1_2_6_modify_article">
</details>

## 1-3단계 ([링크](https://lucas.codesquad.kr/2022-kakao/course/%EC%9B%B9%EB%B0%B1%EC%97%94%EB%93%9C/Kakao-Cafe/%EC%8A%A4%ED%94%84%EB%A7%81-%EC%B9%B4%ED%8E%98-3%EB%8B%A8%EA%B3%84-%EA%B5%AC%ED%98%84))
### 기능 요구사항
- 데이터를 메모리가 아닌 DB에 저장
    - ORM 없이 Spring JDBC만을 사용
    - DB 저장 및 검색에 필요한 SQL은 직접 작성
- 배포
    - 사내 서버 (Krane)을 이용하여 배포 실습
### 상세 구현사항
- DB Schema 구성
    - USERS
        - ID `PK` `Auto Increment`
        - USER_ID
        - PASSWORD
        - NAME
        - EMAIL
    - ARTICLES
        - ARTICLE_ID `PK` `Auto Increment`
        - TITLE
        - WRITER_ID
        - CONTENT
        - DATE
    - COMMENTS
        - ARTICLE_ID `FK`
        - COMMENT_ID `PK` `Auto Increment`
        - WRITER_ID
        - CONTENT
        - DATE
- DataSource 설정을 통해 미리 Schema 및 간단한 Data 구성 후 서버 실행
- 기존에 만들어져있는 `UserRepository`와 `BoardRepository` 인터페이스를 JDBC용으로 새롭게 구현
    - `JdbcTemplate`을 활용하여 DB와 데이터 연동
    - 중복된 ID로 회원가입하는 부분에 대한 예외를 미리 처리
- Krane을 통한 배포
    - JAR 파일을 옮겨 명령어를 통해 실행
### 실행 화면
<details>
    <summary>펼치기</summary>
    <h4>< 배포된 서버로 접속 가능 여부 확인 및 데이터 확인 ></h4>
    <img src="img/step1_3_1_start.gif" alt="step1_3_1_start">
    <h4>< 중복 회원가입 관련 예외 처리 확인 ></h4>
    <img src="img/step1_3_2_duplicated_user.gif" alt="step1_3_2_duplicated_user">
    <h4>< 비밀번호 변경 관련 예외 처리 확인 ></h4>
    <img src="img/step1_3_3_password_wrong.gif" alt="step1_3_3_password_wrong">
</details>
