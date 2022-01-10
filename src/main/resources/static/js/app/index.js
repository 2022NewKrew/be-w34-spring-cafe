var main = {
    init : function () {
        var _this = this;
        $('#btn-enroll').on('click', function () {
            _this.enroll();
        })
        $('#btn-login').on('click', function () {
            _this.login();
        })
        $('#go-home').on('click', function () {
            _this.goHome();
        })
    },
    goHome : function () {
        window.location.href = '/user/list'
    },
    enroll : function () {
        var data = {
            email: $('#email').val(),
            username: $('#username').val(),
            password: $('#password').val()
        };
        $.ajax({
            type: 'POST',
            url: '/api/user',
            dataType: 'json',
            accept: 'application/json',
            contentType: 'application/json; charset-utf-8',
            data: JSON.stringify(data)
        }).done(function () {
            window.location.href = '/user/success';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },
    login : function () {
        var data = {
            email: $('#email').val(),
            password: $('#password').val()
        };
        $.ajax({
            type: 'POST',
            url: '/api/user/login',
            dataType: 'json',
            contentType: 'application/json; charset-utf-8',
            data: JSON.stringify(data)
        }).done(function () {
            alert('로그인 성공');
            window.location.href = '/'; // (1)
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },
};

main.init();