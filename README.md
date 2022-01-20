# be-w34-spring-cafe
| URL                                                  | 기능                                    |
|:-----------------------------------------------------|:--------------------------------------|
| GET /                                                | GET /articles로 redirect (홈)           |
| GET /join-form                                       | join form 불러오기                        |
| POST /login-form                                     | login form 불러오기                       |
| POST /post-form                                      | article post form 불러오기                |
| GET /users                                           | 모든 user 불러오기                          |
| POST /users                                          | 회원정보 수정 내용 DB에 반영                     |
| GET /joined/{userNickName}                           | 회원가입에 성공한 유저 정보 불러오기                  |
| POST /login                                          | 로그인 후 session 추가                      |
| GET /edit-user                                       | 현재 세션에 로그인되어 있는 유저의 정보 불러오기           |
| POST /edit-user/{nickName}                           | 특정 유저의 회원정보 수정 내용 DB에 반영              |
| GET /logout                                          | 현재 세션에 로그인되어 있는 유저 로그아웃               |
| GET /articles                                        | 모든 article 불러오기                       |
| POST /articles                                       | 새로운 article 게시(DB에 삽입)                |
| GET /articles/{articleIdx}                           | 특정 article 정보 가져오기                    |
| PUT /articles/{articleIdx}                           | 특정 article 정보 수정 내용 DB 반영             |
| DELETE /articles/{articleIdx}                        | 특정 article 삭제 DB 반영                   |
| GET /edit-article/{articleIdx}                       | 특정 article 수정 화면 가져오기                 |
| GET /edit-comment/{articleIdx}/comments/{commentIdx} | 특정 article의 특정 댓글 수정 화면 가져오기          |
| PUT /articles/{articleIdx}/comments/{commentIdx}     | 특정 article의 특정 댓글 수정 내용 DB 반영         |
| DELETE /articles/{articleIdx}/comments/{commentIdx}  | 특정 article의 특정 댓글 삭제 DB 반영             |
| POST /comment/s{articleIdx}                          | Create a comment on a certain article |
