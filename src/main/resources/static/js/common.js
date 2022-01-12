/**
 * custom alert using bootstrap
 */
function alertModal() {



}


/**
 * 로그아웃
 */
async function logout() {
  const response = await fetch('/api/logout', {
    method: 'POST'
  });

  if(response.redirected) {
    location.href = response.url;
    return;
  }

  const parsedResponse = await response.json();
  alert(parsedResponse.message + '(' + parsedResponse.code + ')');
}