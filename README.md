# be-w34-spring-cafe

#2일차

### DONE
##### 글쓰기 기능 구현
* qna.controller에 POST 구현
  * 적어도 mockup으로 게시글 보내기
  * qna/form.html을 통해 시도
  * article domain 구현
  * id, writer, title, content 필요
* domain과 controller를 연결할 내부 로직 구현
  * service, repository, DTO
##### 글 목록 기능 구현
* qna.controller에 GET 구현
* repository에서 모든 article 객체를 가져오게 한다.
* 이를 DTO로 변환 후 리스트화하여 동적페이지에 전달
* 동적 페이지에서 이를 받을 수 있게 작업
* domain에 대한 Test code 작성
##### 게시글 상세보기
* article id를 input으로 받는 GET controller method 구현
  * 접근 방식 : /articles/{index}
* repository에서 해당 id를 가지는 article을 가져온다.
* 이를 DTO로 변환 후 리스트화하여 동적페이지에 전달
* 전달할 페이지: qna/show.html 