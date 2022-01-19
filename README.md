# be-w34-spring-cafe

- Login
    - POST /login : 사용자 로그인
    - GET /login : 사용자 로그인 폼
    - GET /logout : 사용자 로그아웃
- User
    - POST /users : 사용자 등록
    - GET /users : 사용자 목록 조회
    - GET /users/{id} : 사용자 프로필 조회
    - PUT /users/{id} : 사용자 수정
    - GET /users/{id}/form : 사용자 업데이트 폼
- Question
    - GET / : 질문 목록 조회
    - POST /questions : 질문 등록
    - GET /questions/{id} : 질문 상세 조회
    - PUT /questions/{id} : 질문 수정
    - DELETE /questions/{id} : 질문 삭제
    - GET /questions/{id}/form : 질문 등록 폼