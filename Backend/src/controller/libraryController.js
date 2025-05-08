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
    getNewUpdateStories_female: async (req, res, next) => {
        try {
            const [rows] = await pool.query(`SELECT get_newupdatestories_female() AS stories`);
            
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

    getFavoriteStories_male: async (req, res, next) => {
        try {
            const [rows] = await pool.query(`SELECT get_topfavoritestories_male() AS stories`);
            
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

    getFavoriteStories_female: async (req, res, next) => {
        try {
            const [rows] = await pool.query(`SELECT get_topfavoritestories_female() AS stories`);
            
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


    getCompletedStories_male: async (req, res, next) => {
        try {
            const [rows] = await pool.query(`SELECT get_completedStories_male() AS stories`);
            
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

    getCompletedStories_female: async (req, res, next) => {
        try {
            const [rows] = await pool.query(`SELECT get_completedStories_female() AS stories`);
            
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

    
    getTopViewCountStories_male: async (req, res, next) => {
        try {
            const [rows] = await pool.query(`SELECT get_TopViewCount_male() AS stories`);
            
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

       
    getTopViewCountStories_female: async (req, res, next) => {
        try {
            const [rows] = await pool.query(`SELECT get_TopViewCount_female() AS stories`);
            
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



};


module.exports = libraryController;
