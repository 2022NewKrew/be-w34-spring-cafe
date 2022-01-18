'use strict';

const SUBMIT = document.getElementById('submit');

let validTitle = true;
let validBody = true;
let validId = true;
let validPassword = true;
let validNewPassword = true;
let validName = true;
let validEmail = true;

function updateSubmitBtn() {
    if (
        validTitle &&
        validBody &&
        validId &&
        validPassword &&
        validNewPassword &&
        validName &&
        validEmail
    ) {
        SUBMIT.disabled = false;
        return;
    }
    SUBMIT.disabled = true;
}

function checkBound(elem, min, max) {
    if (!elem instanceof HTMLInputElement) {
        return false;
    }
    if (typeof(min) !== 'number' || typeof(max) !== 'number') {
        return false;
    }
    const str = elem.value;
    const valid = str.length >= min && str.length <= max;
    elem.style.border = valid ? '' : '1px solid #ffa545';
    return valid;
}

function checkBoundAndRegex(elem, min, max, regex) {
    if (!elem instanceof HTMLInputElement) {
        return false;
    }
    if (typeof(min) !== 'number' || typeof(max) !== 'number') {
        return false;
    }
    if (!regex instanceof RegExp) {
        return false;
    }

    const str = elem.value;
    const valid = str.length >= min && str.length <= max && regex.test(str);
    elem.style.border = valid ? '' : '1px solid #ffa545';
    return valid;
}

const INPUT_TITLE = document.getElementById('title');
const TITLE_MIN = 1;
const TITLE_MAX = 255;

if (INPUT_TITLE !== null && INPUT_TITLE.readOnly === false) {
    SUBMIT.disabled = true;
    INPUT_TITLE.onchange = checkTitle;
    checkTitle();
}

function checkTitle() {
    validTitle = checkBound(INPUT_TITLE, TITLE_MIN, TITLE_MAX);
    updateSubmitBtn();
}

const INPUT_BODY = document.getElementById('body');
const BODY_MIN = 1;
const BODY_MAX = 4095;

if (INPUT_BODY !== null && INPUT_BODY.readOnly === false) {
    SUBMIT.disabled = true;
    INPUT_BODY.onchange = checkBody;
    checkBody();
}

function checkBody() {
    validBody = checkBound(INPUT_BODY, BODY_MIN, BODY_MAX);
    updateSubmitBtn();
}

const INPUT_ID = document.getElementById('id');
const ID_MIN = 6;
const ID_MAX = 12;
const ID_REGEX = /^[0-9a-z]+$/;

if (INPUT_ID !== null && INPUT_ID.readOnly === false) {
    SUBMIT.disabled = true;
    INPUT_ID.onchange = checkId;
    checkId();
}

function checkId() {
    validId = checkBoundAndRegex(INPUT_ID, ID_MIN, ID_MAX, ID_REGEX);
    updateSubmitBtn();
}

const INPUT_PASSWORD = document.getElementById('password');
const PASSWORD_MIN = 8;
const PASSWORD_MAX = 18;
const PASSWORD_REGEX = /^[0-9a-zA-Z]+$/;

if (INPUT_PASSWORD !== null && INPUT_PASSWORD.readOnly === false) {
    SUBMIT.disabled = true;
    INPUT_PASSWORD.onchange = checkPassword;
    checkPassword();
}

function checkPassword() {
    validPassword = checkBoundAndRegex(INPUT_PASSWORD, PASSWORD_MIN, PASSWORD_MAX, PASSWORD_REGEX);
    updateSubmitBtn();
}

const INPUT_NEW_PASSWORD = document.getElementById('newPassword');

if (INPUT_NEW_PASSWORD !== null && INPUT_NEW_PASSWORD.readOnly === false) {
    SUBMIT.disabled = true;
    INPUT_NEW_PASSWORD.onchange = checkNewPassword;
    checkNewPassword();
}

function checkNewPassword() {
    validNewPassword = checkBoundAndRegex(INPUT_NEW_PASSWORD, PASSWORD_MIN, PASSWORD_MAX, PASSWORD_REGEX);
    updateSubmitBtn();
}

const INPUT_NAME = document.getElementById('name');
const NAME_MIN = 1;
const NAME_MAX = 32;

if (INPUT_NAME !== null && INPUT_NAME.readOnly === false) {
    SUBMIT.disabled = true;
    INPUT_NAME.onchange = checkName;
    checkName();
}

function checkName() {
    validName = checkBound(INPUT_NAME, NAME_MIN, NAME_MAX);
    updateSubmitBtn();
}

const INPUT_EMAIL = document.getElementById('email');
const EMAIL_MIN = 1;
const EMAIL_MAX = 32;
const EMAIL_REGEX = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;

if (INPUT_EMAIL !== null && INPUT_EMAIL.readOnly === false) {
    SUBMIT.disabled = true;
    INPUT_EMAIL.onchange = checkEmail;
    checkEmail();
}

function checkEmail() {
    validEmail = checkBoundAndRegex(INPUT_EMAIL, EMAIL_MIN, EMAIL_MAX, EMAIL_REGEX);
    updateSubmitBtn();
}
