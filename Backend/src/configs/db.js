// configs/db.js
const mysql = require('mysql2/promise');

const pool = mysql.createPool({
  host: process.env.SERVER_HOST_DATABASE,
  user: process.env.USERNAME_DATABASE,
  password: process.env.PASSWORD_DATABASE,
  database: process.env.DATABASE,
  port:process.env.PORT_DATABASE,
  waitForConnections: true,
  connectionLimit: 10,
  queueLimit: 0
});

module.exports = pool;
