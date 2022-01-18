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
        $('#btn-qna-post').on('click', function () {
            _this.qnaPost();
        })
        $('#btn-write').on('click', function () {
            _this.write();
        })
        $('#qna-update').on('click', function () {
            _this.qnaUpdate();
        })
        $('#qna-delete').on('click', function () {
            _this.qnaDelete();
        })
    },
    qnaUpdate : function () {
        var data = {
            title: $('#title').val(),
            content: $('#question').val()
        };
        $.ajax({
            type: 'PUT',
            url: '/api/posts/'+$('#post-id').val(),
            dataType: 'json',
            accept: 'application/json',
            contentType: 'application/json; charset-utf-8',
            data: JSON.stringify(data)
        }).done(function () {
            alert("수정완료")
            window.location.href = '/';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },
    qnaDelete : function () {
        console.log($('#post-id').val())
        $.ajax({
            type: 'DELETE',
            url: '/api/posts/'+$('#post-id').val(),
            dataType: 'json',
            accept: 'application/json',
            contentType: 'application/json; charset-utf-8',
        }).done(function () {
            alert("삭제완료")
            window.location.href = '/';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },
    write : function () {
        window.location.href = '/posts/form'
    },
    qnaPost : function () {
        var data = {
            title: $('#title').val(),
            content: $('#question').val(),
            userAccountId: $('#user-id').val()
        };
        $.ajax({
            type: 'POST',
            url: '/api/posts',
            dataType: 'json',
            accept: 'application/json',
            contentType: 'application/json; charset-utf-8',
            data: JSON.stringify(data)
        }).done(function () {
            window.location.href = '/';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },
    goHome : function () {
        window.location.href = '/'
    },
    enroll : function () {
        var data = {
            email: $('#email').val(),
            username: $('#username').val(),
            password: $('#password').val()
        };
        $.ajax({
            type: 'POST',
            url: '/api/users',
            dataType: 'json',
            accept: 'application/json',
            contentType: 'application/json; charset-utf-8',
            data: JSON.stringify(data)
        }).done(function () {
            window.location.href = '/users/success';
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
            url: '/api/users/login',
            dataType: 'json',
            contentType: 'application/json; charset-utf-8',
            data: JSON.stringify(data)
        }).done(function () {
            alert('로그인 성공');
            window.location.href = '/';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },
};

main.init();