// jest.config.ts
export default {
    preset: 'jest-preset-angular',
    testEnvironment: 'jsdom',
    setupFilesAfterEnv: ['<rootDir>/setup-jest.ts'],
    moduleFileExtensions: ['ts', 'html', 'js', 'json'],
    transformIgnorePatterns: ['node_modules/(?!.*\\.mjs$)'],
    testPathIgnorePatterns: ['/node_modules/', '/dist/', '/src/test.ts'],
};


