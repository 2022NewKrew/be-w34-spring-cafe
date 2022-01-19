'use strict';

let validTitle = true;
let validBody = true;
let validId = true;
let validPassword = true;
let validNewPassword = true;
let validName = true;
let validEmail = true;

function checkValidInput() {
    if (validTitle &&
        validBody &&
        validId &&
        validPassword &&
        validNewPassword &&
        validName &&
        validEmail) {
        return true;
    }
    alert("올바른 값이 입력되지 않은 칸이 있습니다. 수정 후 다시 시도해주세요.");
    return false;
}

const INPUT_TITLE = document.getElementById('title');
const TITLE_MIN = 1;
const TITLE_MAX = 255;

if (INPUT_TITLE !== null && INPUT_TITLE.readOnly === false) {
    INPUT_TITLE.onchange = checkTitle;
    checkTitle();
}

function checkTitle() {
    validTitle = checkBound(INPUT_TITLE, TITLE_MIN, TITLE_MAX);
}

const INPUT_BODY = document.getElementById('body');
const BODY_MIN = 1;
const BODY_MAX = 4095;

if (INPUT_BODY !== null && INPUT_BODY.readOnly === false) {
    INPUT_BODY.onchange = checkBody;
    checkBody();
}

function checkBody() {
    validBody = checkBound(INPUT_BODY, BODY_MIN, BODY_MAX);
}

const INPUT_ID = document.getElementById('id');
const ID_MIN = 6;
const ID_MAX = 12;
const ID_REGEX = /^[0-9a-z]+$/;

if (INPUT_ID !== null && INPUT_ID.readOnly === false) {
    INPUT_ID.onchange = checkId;
    checkId();
}

function checkId() {
    validId = checkBoundAndRegex(INPUT_ID, ID_MIN, ID_MAX, ID_REGEX);
}

const INPUT_PASSWORD = document.getElementById('password');
const PASSWORD_MIN = 8;
const PASSWORD_MAX = 18;
const PASSWORD_REGEX = /^[0-9a-zA-Z]+$/;

if (INPUT_PASSWORD !== null && INPUT_PASSWORD.readOnly === false) {
    INPUT_PASSWORD.onchange = checkPassword;
    checkPassword();
}

function checkPassword() {
    validPassword = checkBoundAndRegex(INPUT_PASSWORD, PASSWORD_MIN, PASSWORD_MAX, PASSWORD_REGEX);
}

const INPUT_NEW_PASSWORD = document.getElementById('newPassword');

if (INPUT_NEW_PASSWORD !== null && INPUT_NEW_PASSWORD.readOnly === false) {
    INPUT_NEW_PASSWORD.onchange = checkNewPassword;
    checkNewPassword();
}

function checkNewPassword() {
    validNewPassword = checkBoundAndRegex(INPUT_NEW_PASSWORD, PASSWORD_MIN, PASSWORD_MAX, PASSWORD_REGEX);
}

const INPUT_NAME = document.getElementById('name');
const NAME_MIN = 1;
const NAME_MAX = 32;

if (INPUT_NAME !== null && INPUT_NAME.readOnly === false) {
    INPUT_NAME.onchange = checkName;
    checkName();
}

function checkName() {
    validName = checkBound(INPUT_NAME, NAME_MIN, NAME_MAX);
}

const INPUT_EMAIL = document.getElementById('email');
const EMAIL_MIN = 7;
const EMAIL_MAX = 32;
const EMAIL_REGEX = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;

if (INPUT_EMAIL !== null && INPUT_EMAIL.readOnly === false) {
    INPUT_EMAIL.onchange = checkEmail;
    checkEmail();
}

function checkEmail() {
    validEmail = checkBoundAndRegex(INPUT_EMAIL, EMAIL_MIN, EMAIL_MAX, EMAIL_REGEX);
}
