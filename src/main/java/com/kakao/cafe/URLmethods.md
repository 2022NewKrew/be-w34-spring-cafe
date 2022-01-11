# Application 기능별 URL method
* /
  * GET / : 게시글(질문 목록) 조회
* /user
  * GET /user : 회원 목록 보기(/user/list.html 리다이렉트)
  * POST /user : 회원 가입 후, 리다이렉트(GET /)
  * GET /user/{userId} : 회원 프로필 조회
  * static
    * /user/form.html : 회원 가입 양식
    * /user/login.html : 로그인 양식
    * /user/login_failed.html : 로그인 실패 페이지
* /article
  * GET /article : 글쓰기 양식 화면(/article/form.html 리다이렉트)
  * POST /article : 글 등록 후, 리다이렉트(GET /)
  * GET /article/{index} : 게시물 상세보기(/article/show.html)
  * static
    * /article/form.html : 글쓰기 양식 화면
