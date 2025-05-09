
const pool = require('../configs/db');

/**
 * Cập nhật thông tin người dùng từ danh sách
 * @param {Array<object>} listUser - Danh sách người dùng cần cập nhật
 * @returns {Promise<void>}
 */
async function updateUserByListUser(listUser) {
  if (!Array.isArray(listUser) || listUser.length === 0) {
    throw new Error('Danh sách người dùng không hợp lệ');
  }

  const connection = await pool.getConnection();

  try {
    await connection.beginTransaction();

    const query = `
      UPDATE User SET 
        name = ?, 
        K_elo = ?, 
        LP = ?, 
        rank_lever = ?, 
        image_url = ?
      WHERE id = ?
    `;

    for (const user of listUser) {
      const { id, name, K_elo, LP, rank_lever, image_url } = user;

      await connection.query(query, [
        name,
        K_elo,
        LP,
        rank_lever,
        image_url,
        id
      ]);
    }

    await connection.commit();
  } catch (error) {
    await connection.rollback();
    console.error('❌ Error updating users:', error);
    throw error;
  } finally {
    connection.release();
  }
}

module.exports = updateUserByListUser;
