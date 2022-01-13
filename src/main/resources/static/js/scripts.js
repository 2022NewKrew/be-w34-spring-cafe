String.prototype.format = function () {
    var args = arguments;
    return this.replace(/{(\d+)}/g, function (match, number) {
        return typeof args[number] != 'undefined'
            ? args[number]
            : match
            ;
    });
};

//TODO: js템플릿에서 함수가 있는데 첫번째 진입시 충돌남
function clickLoginModal() {
    let con = document.getElementById("loginModal");
    console.log(con.style.display);
    if (con.style.display !== 'none') {
        con.style.display = 'none';
    }else {
        con.style.display = 'block';
    }
}

(function () {

})();
