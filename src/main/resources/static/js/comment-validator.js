'use strict';

let validComment = true;

function checkValidInput() {
    if (validComment) {
        return true;
    }
    alert("올바른 값이 입력되지 않은 칸이 있습니다. 수정 후 다시 시도해주세요.");
    return false;
}

const INPUT_COMMENT = document.getElementById('comment');
const COMMENT_MIN = 1;
const COMMENT_MAX = 400;

if (INPUT_COMMENT !== null && INPUT_COMMENT.readOnly === false) {
    INPUT_COMMENT.onchange = checkComment;
    checkComment();
}

function checkComment() {
    validComment = checkBound(INPUT_COMMENT, COMMENT_MIN, COMMENT_MAX);
}
