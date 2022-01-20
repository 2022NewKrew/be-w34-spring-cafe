'use strict';

const INPUT_NEW_COMMENT = document.getElementById('comment');
const COMMENT_MIN = 1;
const COMMENT_MAX = 400;
const INVALID = '최대 길이(' + COMMENT_MAX + ')보다 긴 댓글이 입력되었거나 빈 댓글이 입력되었습니다. 수정 후 다시 시도해주세요.';

let validNewComment = true;

function checkValidNewComment() {
    if (validNewComment) {
        return true;
    }
    alert(INVALID);
    return false;
}

if (INPUT_NEW_COMMENT !== null && INPUT_NEW_COMMENT.readOnly === false) {
    INPUT_NEW_COMMENT.onchange = checkComment;
    checkComment();
}

function checkComment() {
    validNewComment = checkBound(INPUT_NEW_COMMENT, COMMENT_MIN, COMMENT_MAX);
}

function openEditComment(idx) {
    const FORM = document.getElementById("editComment" + idx);
    const ORIG_COMMENT = document.getElementById("origComment" + idx);
    const EDIT_COMMENT = document.getElementById("comment" + idx);

    if (FORM === null || ORIG_COMMENT === null || EDIT_COMMENT === null) {
        return;
    }

    ORIG_COMMENT.style.display = 'none';
    FORM.style.display = 'block';
    EDIT_COMMENT.onchange = checkEditComment;

    if (EDIT_COMMENT.value === '') {
        EDIT_COMMENT.value = ORIG_COMMENT.innerText;
        checkBound(EDIT_COMMENT, COMMENT_MIN, COMMENT_MAX);
    }
}

function closeEditComment(idx) {
    const FORM = document.getElementById("editComment" + idx);
    const ORIG_COMMENT = document.getElementById("origComment" + idx);
    const EDIT_COMMENT = document.getElementById("comment" + idx);

    if (FORM === null || ORIG_COMMENT === null || EDIT_COMMENT === null) {
        return;
    }

    ORIG_COMMENT.style.display = 'block';
    FORM.style.display = 'none';
    EDIT_COMMENT.value = '';
    EDIT_COMMENT.onchange = null;
}

function checkEditComment(e) {
    if (!e.target instanceof HTMLInputElement) {
        return;
    }
    checkBound(e.target, COMMENT_MIN, COMMENT_MAX);
}

function checkValidEditComment(idx) {
    const EDIT_COMMENT = document.getElementById("comment" + idx);
    if (checkBound(EDIT_COMMENT, COMMENT_MIN, COMMENT_MAX)) {
        return true;
    }
    alert(INVALID);
    return false;
}
