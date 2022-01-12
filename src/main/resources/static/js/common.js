/**
 * custom alert using bootstrap
 */
function alertModal() {



}

/**
 * 프로필 상세로 이동
 * @param email
 */
function goProfileDetail(email) {
  const encodedEmail = encodeURIComponent(email);
  location.href = '/users/' + encodedEmail;
}

/**
 * 로그아웃
 */
function logout() {
  alert("준비중입니다.");
}