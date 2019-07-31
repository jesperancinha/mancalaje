const MIN_TEXT_LENGTH = 0;

const invalidateText = (text: string) => {
    return text.length === MIN_TEXT_LENGTH;
};

const invalidateEmail = (text: string) => {
    return !/^[a-zA-Z0-9.!#$%&’*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$/.test(text);
};

export {invalidateText, invalidateEmail, MIN_TEXT_LENGTH};
