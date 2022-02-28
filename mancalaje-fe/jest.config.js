module.exports = {
    "testEnvironment": "jsdom",
    "setupFilesAfterEnv": [
        "<rootDir>/src/setuptests.ts"
    ],
    "transform": {
        "\\.tsx?$": "ts-jest",
        "\\.jsx?$": "babel-jest"
    },
    "moduleNameMapper": {
        "\\.(jpg|jpeg|png|gif|eot|otf|webp|ttf|woff|woff2|mp4|webm|wav|mp3|m4a|aac|oga)$": "identity-obj-proxy",
        "\\.(css|less|scss|sass)$": "identity-obj-proxy",
        "\\.(svg)$": "<rootDir>/src/test/mocks/fileMock.js"
    }
}
