# be-w34-spring-cafe

# 1단계
- [x] 회원가입
  - [x] 회원가입 (post)
    - [x] 중복검사 확인
  - [x] 회원가입후 users로 리다이렉트
- [x] 회원 목록
  - [x] users(get) 접근시 list 렌더링 반환
- [x] 회원 조회
  - [x] users/id(get) 로 접근시 profil 렌더링 반환
- [x] html 중복 제거하기

# 2단계
- [x] 글쓰기(post)
  - [x] 완료 후 index 페이지로 redirect
- [x] 글목록 조회(get)
  - [x] 작성한 글 목록 반환
- [x] 상세보기(get)
  - [x] questions/{id} 로 조회

# 3단계
- [x] 피드백 반영
  - [x] 유저정보를 get으로 처리하기
  - [x] 저장할때 putIfAbsent 사용하기
- [ ] jdbc로 변경하기
  - [x] 스키마 추가하기 
  - [ ] 초기데이터 삽입하기
  - [x] user 변경하기
  - [x] qna 변경하기
  - [ ] 예외처리 변경하기
    - [ ] 커스텀 익셉션 만들기
    - [ ] 컨트롤러 어드바이스 만들기
