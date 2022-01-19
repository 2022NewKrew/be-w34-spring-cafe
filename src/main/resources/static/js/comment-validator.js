'use strict';

const SUBMIT = document.getElementById('submit');

let validComment = true;

function updateSubmitBtn() {
    if (
        validComment
    ) {
        SUBMIT.disabled = false;
        return;
    }
    SUBMIT.disabled = true;
}

const INPUT_COMMENT = document.getElementById('comment');
const COMMENT_MIN = 1;
const COMMENT_MAX = 400;

if (INPUT_COMMENT !== null && INPUT_COMMENT.readOnly === false) {
    SUBMIT.disabled = true;
    INPUT_COMMENT.onchange = checkComment;
    checkComment();
}

function checkComment() {
    validEmail = checkBound(INPUT_COMMENT, COMMENT_MIN, COMMENT_MAX);
    updateSubmitBtn();
}
