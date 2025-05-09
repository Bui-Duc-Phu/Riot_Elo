// getListUserByListId.js

const pool = require('../configs/db');

/**
 * Truy vấn danh sách user theo danh sách id
 * @param {Array<number>} listId - Mảng các ID người dùng cần lấy
 * @returns {Promise<Array<object>>} - Mảng đối tượng user
 */
async function getListUserByListId(listId) {
  if (!Array.isArray(listId) || listId.length === 0) {
    throw new Error('Danh sách ID không hợp lệ');
  }

  const placeholders = listId.map(() => '?').join(',');
  const query = `SELECT * FROM User WHERE id IN (${placeholders})`;

  try {
    const [rows] = await pool.query(query, listId);
    return rows;
  } catch (error) {
    console.error('❌ Error fetching users by IDs:', error);
    throw error;
  }
}

module.exports = getListUserByListId;
