
const ErrorEnum = require('./Error');

class CustomError extends Error {
  constructor(errorKey) {
    const error = ErrorEnum[errorKey] || ErrorEnum.INTERNAL_SERVER_ERROR;
    super(error.message);
    this.name = this.constructor.name;
    this.statusCode = error.statusCode;
    Error.captureStackTrace(this, this.constructor);
  }
}

module.exports = CustomError;
