const pool = require('../configs/db');
const CustomError = require('../exception/CustomError');
const avatars = require('../data/avatars.json');

// Function to get random avatar
const getRandomAvatar = () => {
    const randomIndex = Math.floor(Math.random() * avatars.avatars.length);
    return avatars.avatars[randomIndex].url;
};

const mainController = {
    getAllUser: async (req, res, next) => {
        try {
            const [users] = await pool.query(`
                SELECT 
                    id,
                    name,
                    K_elo as kElo,
                    LP as lp,
                    win,
                    rank_lever as rankLever,
                    image_url as imageUrl
                FROM User
            `);

            // Convert LP to numbers and sort for top players
            const usersWithLp = users.map(user => ({
                ...user,
                lp: parseInt(user.lp) || 0
            }));
            const sortedUsers = [...usersWithLp].sort((a, b) => b.lp - a.lp);
            const top30 = sortedUsers.slice(0, 30);
            const top10 = sortedUsers.slice(0, 10);

            // Convert numeric values to strings and calculate rank
            const formattedUsers = users.map(user => {
                const lp = parseInt(user.lp) || 0;
                let rank;
                let rankNumber;

                // Calculate base rank
                if (lp >= 3000) {
                    rank = "Cao Thủ";
                    rankNumber = 3;
                } else if (lp >= 2000) {
                    rank = "Kim Cương";
                    rankNumber = 4;
                } else if (lp >= 1000) {
                    rank = "Bạch Kim";
                    rankNumber = 5;
                } else if (lp >= 600) {
                    rank = "Vàng";
                    rankNumber = 6;
                } else if (lp >= 400) {
                    rank = "Bạc";
                    rankNumber = 7;
                } else if (lp >= 0) {
                    rank = "Đồng";
                    rankNumber = 8;
                }

                // Add rank level (I, II, III, IV)
                if (rank === "Đồng") {
                    const level = Math.floor((lp / 200) * 4) + 1;
                    rank += ` ${Math.min(level, 4)}`;
                } else if (rank === "Bạc") {
                    const level = Math.floor(((lp - 200) / 200) * 4) + 1;
                    rank += ` ${Math.min(level, 4)}`;
                } else if (rank === "Vàng") {
                    const level = Math.floor(((lp - 400) / 200) * 4) + 1;
                    rank += ` ${Math.min(level, 4)}`;
                } else if (rank === "Bạch Kim") {
                    const level = Math.floor(((lp - 1000) / 1000) * 4) + 1;
                    rank += ` ${Math.min(level, 4)}`;
                } else if (rank === "Kim Cương") {
                    const level = Math.floor(((lp - 2000) / 1000) * 4) + 1;
                    rank += ` ${Math.min(level, 4)}`;
                }

                // Check for Đại Cao Thủ and Thách Đấu
                if (top30.some(topUser => topUser.id === user.id)) {
                    rank = "Đại Cao Thủ";
                    rankNumber = 2;
                }
                if (top10.some(topUser => topUser.id === user.id)) {
                    rank = "Thách Đấu";
                    rankNumber = 1;
                }

                return {
                    id: user.id.toString(),
                    name: user.name,
                    kElo: user.kElo ? user.kElo.toString() : null,
                    lp: user.lp ? user.lp.toString() : null,
                    win: user.win.toString(),
                    rankLever: rank,
                    rank: rankNumber,
                    imageUrl: user.imageUrl || getRandomAvatar()
                };
            });

            // Sort final result by LP in descending order
            const sortedResult = formattedUsers.sort((a, b) => {
                const lpA = parseInt(a.lp) || 0;
                const lpB = parseInt(b.lp) || 0;
                return lpB - lpA;
            });

            return res.status(200).json({
                status: 'success',
                result: sortedResult
            });

        } catch (err) {
            console.error('Error getting new update stories:', err);
            return next(new CustomError('INTERNAL_SERVER_ERROR'));
        }
    },

    getDetailUserById: async (req, res, next) => {
        try {
            console.log('Request body:', req.body);  // Log request body
            const userId = req.body.id;

            if (!userId) {
                console.log("id user not have" + userId);
                return next(new CustomError('MISSING_USER_ID'));
            }

            console.log('User ID:', userId);  // Log user ID

            // Get user basic info
            const [users] = await pool.query(`
                SELECT 
                    id,
                    name,
                    K_elo as kElo,
                    LP as lp,
                    win,
                    rank_lever as rankLever,
                    image_url as imageUrl
                FROM User
                WHERE id = ?
            `, [userId]);

            console.log('Query result:', users);  // Log query result

            if (users.length === 0) {
                return next(new CustomError('USER_NOT_FOUND'));
            }

            const user = users[0];

            // Get total matches and win rate
            const [matchStats] = await pool.query(`
                SELECT 
                    COUNT(*) as totalMatches,
                    SUM(CASE 
                        WHEN (m.winner = 'teams1' AND JSON_CONTAINS(m.teams1, ?)) OR 
                             (m.winner = 'teams2' AND JSON_CONTAINS(m.teams2, ?))
                        THEN 1 
                        ELSE 0 
                    END) as totalWins
                FROM Matches m
                WHERE JSON_CONTAINS(m.teams1, ?) OR JSON_CONTAINS(m.teams2, ?)
            `, [userId, userId, userId, userId]);

            console.log('Match stats:', matchStats);  // Log match stats

            const totalMatches = matchStats[0].totalMatches || 0;
            const totalWins = matchStats[0].totalWins || 0;
            const winRate = totalMatches > 0 ? ((totalWins / totalMatches) * 100).toFixed(2) : 0;

            // Calculate rank and rank number
            const lp = parseInt(user.lp) || 0;
            let rank = user.rankLever;
            let rankNumber;

            if (lp >= 3000) {
                rank = "Cao Thủ";
                rankNumber = 3;
            } else if (lp >= 2000) {
                rank = "Kim Cương";
                rankNumber = 4;
            } else if (lp >= 1000) {
                rank = "Bạch Kim";
                rankNumber = 5;
            } else if (lp >= 600) {
                rank = "Vàng";
                rankNumber = 6;
            } else if (lp >= 400) {
                rank = "Bạc";
                rankNumber = 7;
            } else if (lp >= 0) {
                rank = "Đồng";
                rankNumber = 8;
            }

            // Add rank level (I, II, III, IV)
            if (rank === "Đồng") {
                const level = Math.floor((lp / 200) * 4) + 1;
                rank += ` ${Math.min(level, 4)}`;
            } else if (rank === "Bạc") {
                const level = Math.floor(((lp - 200) / 200) * 4) + 1;
                rank += ` ${Math.min(level, 4)}`;
            } else if (rank === "Vàng") {
                const level = Math.floor(((lp - 400) / 200) * 4) + 1;
                rank += ` ${Math.min(level, 4)}`;
            } else if (rank === "Bạch Kim") {
                const level = Math.floor(((lp - 1000) / 1000) * 4) + 1;
                rank += ` ${Math.min(level, 4)}`;
            } else if (rank === "Kim Cương") {
                const level = Math.floor(((lp - 2000) / 1000) * 4) + 1;
                rank += ` ${Math.min(level, 4)}`;
            }

            // Check for Đại Cao Thủ and Thách Đấu
            const [topPlayers] = await pool.query(`
                SELECT id, LP
                FROM User
                ORDER BY LP DESC
                LIMIT 30
            `);

            const top30 = topPlayers.slice(0, 30);
            const top10 = topPlayers.slice(0, 10);

            if (top30.some(player => player.id === parseInt(userId))) {
                rank = "Đại Cao Thủ";
                rankNumber = 2;
            }
            if (top10.some(player => player.id === parseInt(userId))) {
                rank = "Thách Đấu";
                rankNumber = 1;
            }

            return res.status(200).json({
                status: 'success',
                result: {
                    id: user.id.toString(),
                    name: user.name,
                    kElo: user.kElo ? user.kElo.toString() : null,
                    lp: user.lp ? user.lp.toString() : null,
                    win: user.win.toString(),
                    rankLever: rank,
                    rank: rankNumber,
                    imageUrl: user.imageUrl || getRandomAvatar(),
                    totalMatches: totalMatches,
                    totalWins: totalWins,
                    winRate: `${winRate}%`
                }
            });

        } catch (err) {
            console.error('Error getting user details:', err);
            console.error('Error stack:', err.stack);  // Log error stack
            return next(new CustomError('INTERNAL_SERVER_ERROR'));
        }
    },

    getAllMatchesByIdUser: async (req, res, next) => {
        try {
            const userId = req.body.id;

            if (!userId) {
                return next(new CustomError('MISSING_USER_ID'));
            }

            // Get all matches and match info for the user
            const [matches] = await pool.query(`
                SELECT 
                    m.id as matchId,
                    mi.id,
                    u.name as userName,
                    mi.champion,
                    mi.position,
                    mi.gold,
                    mi.kills,
                    mi.deaths,
                    mi.assists,
                    CASE 
                        WHEN (m.winner = 'teams1' AND JSON_CONTAINS(m.teams1, ?)) OR 
                             (m.winner = 'teams2' AND JSON_CONTAINS(m.teams2, ?))
                        THEN true 
                        ELSE false 
                    END as isWin
                FROM Matches m
                JOIN MatchInfo mi ON m.id = mi.match_id
                JOIN User u ON mi.user_id = u.id
                WHERE mi.user_id = ?
                ORDER BY m.id DESC
                LIMIT 50
            `, [userId, userId, userId]);

            // Format the response to match the Kotlin data class
            const formattedMatches = matches.map(match => ({
                id: match.id.toString(),
                matchId: match.matchId.toString(),
                userName: match.userName,
                champion: match.champion,
                imageChampion: `https://ddragon.leagueoflegends.com/cdn/13.24.1/img/champion/${match.champion}.png`,
                position: match.position,
                gold: match.gold.toString(),
                kills: match.kills.toString(),
                deaths: match.deaths.toString(),
                assists: match.assists.toString(),
                isWin: match.isWin
            }));

            return res.status(200).json({
                status: 'success',
                result: formattedMatches
            });

        } catch (err) {
            console.error('Error getting user matches:', err);
            return next(new CustomError('INTERNAL_SERVER_ERROR'));
        }
    },

    getAllTeamsMatcherByIdMatch: async (req, res, next) => {
        try {
            const matchId = req.body.id;

            if (!matchId) {
                return next(new CustomError('MISSING_MATCH_ID'));
            }

            // Get match details and all players' information
            const [matchDetails] = await pool.query(`
                SELECT 
                    m.id as matchId,
                    m.teams1,
                    m.teams2,
                    m.winner,
                    m.match_time as matchTime,
                    mi.id,
                    mi.user_id as userId,
                    u.name as userName,
                    mi.champion,
                    mi.position,
                    mi.gold,
                    mi.kills,
                    mi.deaths,
                    mi.assists,
                    CASE 
                        WHEN (m.winner = 'teams1' AND JSON_CONTAINS(m.teams1, CAST(mi.user_id AS JSON))) OR 
                             (m.winner = 'teams2' AND JSON_CONTAINS(m.teams2, CAST(mi.user_id AS JSON)))
                        THEN true 
                        ELSE false 
                    END as isWin
                FROM Matches m
                JOIN MatchInfo mi ON m.id = mi.match_id
                JOIN User u ON mi.user_id = u.id
                WHERE m.id = ?
                ORDER BY mi.position
            `, [matchId]);

            if (matchDetails.length === 0) {
                return next(new CustomError('MATCH_NOT_FOUND'));
            }

            // Format the response
            const formattedMatch = matchDetails.map(player => ({
                id: player.id.toString(),
                matchId: player.matchId.toString(),
                userName: player.userName,
                champion: player.champion,
                imageChampion: `https://ddragon.leagueoflegends.com/cdn/13.24.1/img/champion/${player.champion}.png`,
                position: player.position,
                gold: player.gold.toString(),
                kills: player.kills.toString(),
                deaths: player.deaths.toString(),
                assists: player.assists.toString(),
                isWin: player.isWin
            }));

            return res.status(200).json({
                status: 'success',
                result: formattedMatch
            });

        } catch (err) {
            console.error('Error getting match details:', err);
            return next(new CustomError('INTERNAL_SERVER_ERROR'));
        }
    },

};

module.exports = mainController;



