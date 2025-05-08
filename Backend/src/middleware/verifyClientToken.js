const jwt = require('jsonwebtoken');
const CustomError = require('../exception/CustomError');  // Giả sử bạn có class CustomError

function verifyClientToken(req, res, next) {
    const token = req.headers['x-access-token'];

    if (!token) {
        return next(new CustomError('TOKEN_MISSING'));  // Sử dụng CustomError để ném lỗi
    }

    try {
        if (!process.env.SECRET_KEY) {
            return next(new CustomError('SECRET_KEY_NOT_FOUND'));  // Kiểm tra SECRET_KEY
        }
        
        const decoded = jwt.verify(token, process.env.SECRET_KEY);
        req.client = decoded;  // Gắn thông tin vào request
        next();
    } catch (err) {
        return next(new CustomError('INVALID_OR_EXPIRED_TOKEN'));  // Sử dụng CustomError để ném lỗi
    }
}

module.exports = { verifyClientToken };
