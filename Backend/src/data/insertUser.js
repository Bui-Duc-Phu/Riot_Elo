// insertUsersFromJson.js

require('dotenv').config();
const pool = require('../configs/db');
const fs = require('fs');
const path = require('path');

const filePath = path.join(__dirname, '../data/user2.json'); // Đường dẫn đến file JSON

let users;

try {
  const fileData = fs.readFileSync(filePath, 'utf-8');
  users = JSON.parse(fileData);
} catch (err) {
  console.error('❌ Error reading users.json:', err);
  process.exit(1);
}

async function insertUsers(users) {
  try {
    for (const user of users) {
      const { name, K_elo,LP,rank_lever,image_url } = user;
      const [result] = await pool.query(
        'INSERT INTO User (name, K_elo, LP, rank_lever, image_url) VALUES (?, ?, ?, ?, ?)',
        [name, K_elo || 40, LP || 0, rank_lever || null, image_url || null]
      );
      
      console.log(`✅ Inserted ${name} with ID: ${result.insertId}`);
    }
    console.log('🎉 All users inserted successfully');
    process.exit(0);
  } catch (error) {
    console.error('❌ Error inserting users:', error);
    process.exit(1);
  }
}

insertUsers(users);
