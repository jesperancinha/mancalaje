'use strict'
const baseConfig = require('../../jest.config.base')

const packageName = require('./package.json').name.split('@assignar/').pop()

module.exports = {
    roots: [
        `<rootDir>/packages/${packageName}`,
    ],
    collectCoverageFrom: [
        'src/**/*.{ts,tsx}',
    ],
    setupFiles: [
        `<rootDir>/packages/${packageName}/jest/polyfills.ts`,
        `<rootDir>/packages/${packageName}/jest/setupEnzyme.ts`,
    ],
    setupTestFrameworkScriptFile: `<rootDir>/packages/${packageName}/jest/setupJest.ts`,
    testRegex: `(packages/${packageName}/.*/__tests__/.*|\\.(test|spec))\\.tsx?$`,
    testURL: 'http://localhost/',
    moduleNameMapper: {
        '.json$': 'identity-obj-proxy',
        'lodash-es': 'lodash',
    },
    moduleDirectories: [
        'node_modules',
    ],
    modulePaths: [
        `<rootDir>/packages/${packageName}/src/`,
    ],
    snapshotSerializers: [
        'enzyme-to-json/serializer',
    ],
    name: packageName,
    displayName: packageName,
    rootDir: '../..',
}