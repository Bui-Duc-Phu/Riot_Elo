// configs/db.js
const mysql = require('mysql2/promise');

const pool = mysql.createPool({
  host: process.env.SERVER_HOST_DATABASE || '167.86.74.239',
  user: process.env.USERNAME_DATABASE || 'ducphu2',
  password: process.env.PASSWORD_DATABASE || 'phuhk123',
  database: process.env.DATABASE || 'Riot_Elo',
  port:process.env.PORT_DATABASE || 3306,
  waitForConnections: true,
  connectionLimit: 10,
  queueLimit: 0
});

module.exports = pool;
