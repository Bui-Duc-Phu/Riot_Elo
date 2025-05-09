const pool = require('../configs/db');
const CustomError = require('../exception/CustomError');

const libraryController = {
    getNewUpdateStories_male: async (req, res, next) => {
        try {
            const [rows] = await pool.query(`SELECT get_newupdatestories_male() AS stories`);
            
            const storiesJson = rows[0]?.stories || [];

            return res.status(200).json({
                message: 'Access granted',
                result: storiesJson
            });
        } catch (err) {
            console.error('Error getting new update stories:', err);
            return next(new CustomError('INTERNAL_SERVER_ERROR'));
        }
    },

    insertUser: async (req, res, next) => {
        try {
            const { name, K_elo } = req.body;

            if (!name) {
                return next(new CustomError('BAD_REQUEST', 'Name is required'));
            }

            const [result] = await pool.query(
                'INSERT INTO User (name, K_elo) VALUES (?, ?)',
                [name, K_elo || null]
            );

            return res.status(201).json({
                message: 'User created successfully',
                userId: result.insertId
            });
        } catch (err) {
            console.error('Error inserting user:', err);
            return next(new CustomError('INTERNAL_SERVER_ERROR'));
        }
    }
};

module.exports = libraryController;
