// jest.config.js
module.exports = {
    testEnvironment: 'jsdom',
    transform: {
      '^.+\\.(js|jsx)$': 'babel-jest',
      '\\.(css)$': 'jest-transform-stub', 
    },
  };
  