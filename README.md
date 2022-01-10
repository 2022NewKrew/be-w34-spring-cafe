# be-w34-spring-cafe

## Step 1-1

### 회원가입, 사용자 목록 기능 구현
* 카페 깃헙 저장소를 기반으로 실습을 진행한다.
* Lucas 브랜치 생성 기능이 오류날 경우 깃헙 저장소에서 직접 브랜치를 생성한다. (현재 미리 생성되어 있음)
* 요구사항에 대한 구현을 완료한 후 자신의 github 아이디에 해당하는 브랜치에 Pull Request를 통해 리뷰 요청을 한다.

### 학습 참고 자료
* https://github.com/spring-projects/spring-petclinic
* https://jojoldu.tistory.com/250

### 웹페이지 디자인
* static 폴더에 있는 기존 자료(QA 게시판) 를 수정하거나 아래 디자인 페이지를 참고해서 구현한다.
* [디자인 페이지 참고자료](https://www.figma.com/file/nwhBasptomWJCAMkElxp74/%EC%9E%90%EB%B0%94%EB%B0%B1%EC%97%94%EB%93%9C%EA%B5%90%EC%9C%A1%EC%9A%A9%EC%9B%B9%ED%8E%98%EC%9D%B4%EC%A7%80?node-id=0%3A1)

### 각 기능에 따른 url과 메서드 convention
* 먼저 각 기능에 대한 대표 url을 결정한다.
  * 예를 들어 회원 관리와 관련한 기능의 대표 URL은 "/users"와 같이 설계한다.
* 기능의 세부 기능에 대해 일반적으로 구현하는 기능(목록, 상세보기, 수정, 삭제)에 대해서는 URL convention을 결정한 후 사용할 것을 추천한다.
  * 예를 들어 todo 앱에 대한 URL convention을 다음과 같은 기준으로 잡을 수 있다.

| url                      | 	기능                           |
|--------------------------|-------------------------------|
| GET /todos               | 	List all todos               |
| POST /todos              | 	Create a new todo            |
| GET /todos/:id           | 	Get a todo                   |
| PUT /todos/:id           | 	Update a todo                |
| DELETE /todos/:id        | 	Delete a todo and its items  |
| GET /todos/:id/items     | 	Get a todo item              |
| PUT /todos/:id/items     | 	Update a todo item           |
| DELETE /todos/:id/items  | 	Delete a todo item           |
