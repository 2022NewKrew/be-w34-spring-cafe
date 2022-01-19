# be-w34-spring-cafe

### 배포 : http://krane-test-leo.ay1.krane.9rum.cc:8080/

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

### Step4 구현 사항
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

