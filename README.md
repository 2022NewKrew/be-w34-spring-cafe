# be-w34-spring-cafe
| URL                         | 기능                                    |
|:----------------------------|:--------------------------------------|
| GET /                       | 모든 article 불러오기(홈)                    |
| GET /join-form              | join form 불러오기                        |
| POST /login-form            | login form 불러오기                       |
| POST /post-form             | article post form 불러오기                |
| GET /users                  | 모든 user 불러오기                          |
| POST /users                 | 회원정보 수정 내용 DB에 반영                     |
| GET /joined/{userNickName}  | 회원가입에 성공한 유저 정보 불러오기                  |
| POST /login                 | 로그인 후 session 추가                      |
| GET /edit-user              | 현재 세션에 로그인되어 있는 유저의 정보 불러오기           |
| POST /edit-user/{nickName}  | 특정 유저의 회원정보 수정 내용 DB에 반영              |
| GET /logout                 | 현재 세션에 로그인되어 있는 유저 로그아웃               |
| POST /articles              | 새로운 article 게시(DB에 삽입)                |
| GET /articles/{articleIdx}  | 특정 article 정보 가져오기                    |
| POST /comment/s{articleIdx} | Create a comment on a certain article |
