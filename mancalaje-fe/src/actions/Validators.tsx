const MIN_TEXT_LENGTH = 0;

const invalidateText = (text: string) => {
    return text.length === MIN_TEXT_LENGTH;
};

export {invalidateText, MIN_TEXT_LENGTH}