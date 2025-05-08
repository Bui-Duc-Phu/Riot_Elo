const CustomError = require('../exception/CustomError'); 

const errhandlerException =  (err, req, res, next) => {
  console.error(err);

  // Nếu lỗi là instance của CustomError, sử dụng thông tin từ nó
  if (err instanceof CustomError) {
    return res.status(err.statusCode).json({
      status: err.statusCode,
      message: err.message,
    });
  }
  // Nếu không, trả về lỗi mặc định
  res.status(500).json({
    status: 500,
    message: err.message || 'Internal Server Error'
  });
  };
module.exports = errhandlerException;



