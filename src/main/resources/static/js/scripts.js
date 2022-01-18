String.prototype.format = function() {
  var args = arguments;
  return this.replace(/{(\d+)}/g, function(match, number) {
    return typeof args[number] != 'undefined'
        ? args[number]
        : match
        ;
  });
};

(function logOutHandler() {
  document.querySelector('#logout').addEventListener('click', (e) => {
    fetch("/auth/logout", {
      method: "POST",
    }).then((response) => {
      location.href = response.url;
    });
  })
})();
