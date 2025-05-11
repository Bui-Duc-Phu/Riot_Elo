const pool = require('../../configs/db');
const genMatches = require('../genMatches');
const calculateTeamElo = require('../calculateTeamElo');

async function pushMatchData(teams1Obj, teams2Obj, winnerTeams) {
    try {
        // Get current timestamp for match_time
        const matchTime = new Date().toISOString().slice(0, 19).replace('T', ' ');

        // Extract only user IDs from teams
        const teams1Ids = teams1Obj.map(player => player.idUser);
        const teams2Ids = teams2Obj.map(player => player.idUser);

        // Insert into Matches table with only user IDs
        const [matchResult] = await pool.execute(
            'INSERT INTO Matches (teams1, teams2, winner, match_time) VALUES (?, ?, ?, ?)',
            [
                JSON.stringify(teams1Ids),
                JSON.stringify(teams2Ids),
                winnerTeams ? 'teams1' : 'teams2',
                matchTime
            ]
        );

        const matchId = matchResult.insertId;

        // Insert match info for team 1
        for (const player of teams1Obj) {
            await pool.execute(
                'INSERT INTO MatchInfo (match_id, user_id, champion, position, gold, kills, deaths, assists) VALUES (?, ?, ?, ?, ?, ?, ?, ?)',
                [
                    matchId,
                    player.idUser,
                    player.champion,
                    player.Position,
                    player.Gold,
                    player.Kills,
                    player.Death,
                    player.Assists
                ]
            );
        }

        // Insert match info for team 2
        for (const player of teams2Obj) {
            await pool.execute(
                'INSERT INTO MatchInfo (match_id, user_id, champion, position, gold, kills, deaths, assists) VALUES (?, ?, ?, ?, ?, ?, ?, ?)',
                [
                    matchId,
                    player.idUser,
                    player.champion,
                    player.position,
                    player.gold,
                    player.kills,
                    player.death,
                    player.assists
                ]
            );
        }

        // Calculate new Elo and LP for all players
        const updatedUsers = await calculateTeamElo(teams1Obj, teams2Obj, winnerTeams);
        console.log('Updated users data:', updatedUsers);

        // Update LP, win streak, and K_elo for all users in the database
        for (const user of updatedUsers) {
            // Get current user data
            const [userData] = await pool.execute(
                'SELECT win, K_elo FROM User WHERE id = ?',
                [user.id]
            );
            const currentUser = userData[0];
            
            let newWin = currentUser.win;
            let newK_elo = currentUser.K_elo;

            // Determine if user is in winning team
            const isInWinningTeam = winnerTeams ? 
                teams1Obj.some(p => p.idUser === user.id) : 
                teams2Obj.some(p => p.idUser === user.id);

            console.log(`Processing user ${user.id}:`);
            console.log(`- Current win streak: ${newWin}`);
            console.log(`- Is in winning team: ${isInWinningTeam}`);

            // Update win streak - Simplified logic
            if (isInWinningTeam) {
                // Team thắng: win luôn > 0
                newWin = Math.max(1, newWin + 1);
            } else {
                // Team thua: win luôn < 0
                newWin = Math.min(-1, newWin - 1);
            }

            // Update K_elo based on win streak
            if (newWin > 2 && newK_elo < 100) {
                newK_elo += 10;
            } else if (newWin < -2 && newK_elo > 20) {
                newK_elo -= 10;
            }

            console.log(`- New win streak: ${newWin}`);
            console.log(`- New K_elo: ${newK_elo}`);

            // Update user data in database
            await pool.execute(
                'UPDATE User SET LP = ?, win = ?, K_elo = ? WHERE id = ?',
                [user.LP, newWin, newK_elo, user.id]
            );
        }

        return {
            matchId,
            updatedUsers
        };

    } catch (error) {
        console.error('Error pushing match data:', error);
        throw error;
    }
}

// Test function for single match
async function testPushMatchData(userMain, allUsers) {
    try {
        const { teams1Obj, teams2Obj, winnerTeams, MVPPlayer1, MVPPlayer2 } = genMatches(allUsers, userMain);
        
        // Push data to database and get updated users
        const { matchId, updatedUsers } = await pushMatchData(teams1Obj, teams2Obj, winnerTeams, MVPPlayer1, MVPPlayer2);
        
        console.log(`Match created for user ${userMain} with ID: ${matchId}`);
        return { matchId, updatedUsers };
    } catch (error) {
        console.error(`Error in match for user ${userMain}:`, error.message);
        throw error;
    }
}

// Test function for all users
async function testAllUserPushData() {
    try {
        // Get all user IDs from User table - only once
        const [users] = await pool.execute('SELECT id FROM User');
        const allUsers = users.map(user => user.id);
        
        console.log(`Starting matches for ${allUsers.length} users (10 matches per user)...`);
        
        // Process users in batches of 5 for parallel execution
        const batchSize = 5;
        for (let i = 0; i < allUsers.length; i += batchSize) {
            const batch = allUsers.slice(i, i + batchSize);
            console.log(`\nProcessing batch ${Math.floor(i/batchSize) + 1} of ${Math.ceil(allUsers.length/batchSize)}`);
            
            // Process batch in parallel
            await Promise.all(
                batch.map(async (userMain) => {
                    try {
                        console.log(`\nCreating 10 matches for user ${userMain}:`);
                        // Create 10 matches for each user
                        for (let matchNum = 1; matchNum <= 10; matchNum++) {
                            try {
                                const { matchId } = await testPushMatchData(userMain, allUsers);
                                console.log(`Match ${matchNum}/10 created with ID: ${matchId}`);
                                // Small delay between matches for the same user
                                await new Promise(resolve => setTimeout(resolve, 200));
                            } catch (matchError) {
                                console.error(`Failed to create match ${matchNum} for user ${userMain}:`, matchError.message);
                            }
                        }
                    } catch (error) {
                        console.error(`Failed to process user ${userMain}:`, error.message);
                    }
                })
            );
            
            // Small delay between batches to prevent database overload
            if (i + batchSize < allUsers.length) {
                console.log('\nWaiting before next batch...');
                await new Promise(resolve => setTimeout(resolve, 1000));
            }
        }
        
        console.log('\nAll matches have been created successfully!');
    } catch (error) {
        console.error('Test failed:', error.message);
        throw error;
    }
}

// Run test for all users
testAllUserPushData().catch(error => {
    console.error('Test failed with error:', error.message);
    process.exit(1);
});

module.exports = pushMatchData;






