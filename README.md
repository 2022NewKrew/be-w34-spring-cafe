# be-w34-spring-cafe

### 배포 : http://krane-test-leo.ay1.krane.9rum.cc
- using docker
- d2hub 를 사용해서 krane 에 배포하기에는 음..
- 첨에 이미지를 scp로 옮겨서 배포했으나 jm 의 공유로 d2hub 에서 push, pull 하여 배포

### Install
```
$sudo chmod 750 gradlew
$./gradlew build
$./gradlew bootRun

# See mysql
# krane-test-leo.ay1.krane.9rum.cc:3306/leojung
# id : leojung
# pw : 1234
```

### Step2-3, 2-4 구현 사항
- 로그인 시 Referer 값으로 리다이렉트하여 이전 화면으로 이동
- Comment 도메인 추가 및 조회, 신규 API 개발
- 게시글 + 댓글 OneToMany 단방향 객체 매핑.. 불완전
	- 게시글 select 시에만 댓글 객체 불러오고, save 시에는 저장x
- Join 활용하여 게시글 조회시 댓글에 대한 N+1문제 방지하고자 하나 이게 맞게 한건지..
- jdbcTemplate 으로 온전히 객체지향적인 매핑을 구현하기가 쉽지 않았습니다.
- Jpa 공부를 해보면서 부족한 부분을 나중에 채워나가야 할듯.


### Step2-1, 2-2, 2-5 구현 사항
- null 처리 Optional 로 변경 및 domain 생성 및 null 처리에 대한 원칙 재정의
- Bcrypt 패스워드 해싱 적용
- put 로직 개선 - update 를 없애고(일부 조회수 쿼리 등에 한정적으로 사용) save 함수를 개선하여 업데이트
	- save 함수: 기존 존재유무 조회 후 insert 나 update 로 쿼리 분기
- Mysql krane 설치 및 DB 세팅 변경(h2 -> mysql)
- Email 도메인 추가 및 검증 로직 추가
- RequireLogin 어노테이션 신규
	- RequireLogin 이 달린 메소드는 로그인 여부를 사전에 체크하여 로그인 화면으로 리다이렉트
- Soft delete 구현, is_deleted 칼럼 추가 및 Enum 신규
- 게시글 수정하기 및 삭제 구현
- 게시글 order by 안먹히는 현상 수정
- 내 프로필 수정하기 구현
- javadocs 일부 추가, 짬짬이 추가중.

### Step1 구현 사항
- 회원가입
- 회원목록 조회
- mustache + bootstrap 템플릿은 따로 가져와 커스텀했습니다..
- spring jdbc 연결 - h2

### Step2, 3, 구현 사항
- 로그인(Session + Aspect) 및 로그인에 따른 UI 처리
- 게시판 목록조회, 글 읽기, 새 글 작성

