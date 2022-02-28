const MIN_TEXT_LENGTH = 0;

const regExp = new RegExp("^[a-zA-Z0-9.!#$%&’*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$");

const invalidateText = (text: string) => {
    return text.length === MIN_TEXT_LENGTH;
};

const invalidateEmail = (text: string) => {
    return !regExp.test(text);
};

export {invalidateText, invalidateEmail, MIN_TEXT_LENGTH};
