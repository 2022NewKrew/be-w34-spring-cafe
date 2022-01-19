'use strict';

const BORDER_STYLE = '1px solid #22cc22';

function checkBound(elem, min, max) {
    if (!elem instanceof HTMLInputElement) {
        return false;
    }
    if (typeof(min) !== 'number' || typeof(max) !== 'number') {
        return false;
    }
    const str = elem.value;
    const valid = str.length >= min && str.length <= max;
    elem.style.border = valid ? BORDER_STYLE : '';
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
    elem.style.border = valid ? BORDER_STYLE : '';
    return valid;
}
