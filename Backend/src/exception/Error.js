
const ErrorEnum = Object.freeze({
     // Thêm các lỗi khác tại đây
    INVALID_EMAIL: { message: 'Email không hợp lệ', statusCode: 400 },
    USER_NOT_FOUND: { message: 'Người dùng không tìm thấy', statusCode: 404 },
    INTERNAL_SERVER_ERROR: { message: 'Lỗi máy chủ nội bộ', statusCode: 500 },
    MISSING_DEVICE_ID: { message: 'Thiếu device_id', statusCode: 400 },
    TOKEN_MISSING: { message: 'Token thiếu', statusCode: 401 },
    SECRET_KEY_NOT_FOUND: { message: 'Không tìm thấy SECRET_KEY', statusCode: 500 },
    INVALID_OR_EXPIRED_TOKEN: { message: 'Token không hợp lệ hoặc hết hạn', statusCode: 401 },
  });
  
  module.exports = ErrorEnum;
  