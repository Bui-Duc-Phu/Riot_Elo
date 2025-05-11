const getListUserByListId = require('./getListUserByListId');
const calculateElo = require('./calculateElo');

async function calculateTeamElo(teams1, teams2, winnerTeams, MVPPlayer1, MVPPlayer2) {
    const userIds1 = teams1.map(user => user.idUser);
    const userIds2 = teams2.map(user => user.idUser);

    const ListUser1 = await getListUserByListId(userIds1);
    const ListUser2 = await getListUserByListId(userIds2);

    // Ensure we're using consistent property naming - LP is stored as "LP" in DB but as "Lp" in returned objects
    const allLp1 = ListUser1.reduce((sum, user) => sum + (user.Lp || user.LP || 0), 0);
    const allLp2 = ListUser2.reduce((sum, user) => sum + (user.Lp || user.LP || 0), 0);

    let user1 = ListUser1[Math.floor(Math.random() * ListUser1.length)];
    let user2 = ListUser2[Math.floor(Math.random() * ListUser2.length)];

    let Elo1_thuong = calculateElo({ 
        K: user1.K_elo, 
        LPTeam1: allLp1, 
        LPTeam2: allLp2, 
        isWin: winnerTeams, 
        isMVP: false 
    });
    
    let Elo1_Mvp = calculateElo({ 
        K: user1.K_elo, 
        LPTeam1: allLp1, 
        LPTeam2: allLp2, 
        isWin: winnerTeams, 
        isMVP: true 
    });
    
    let Elo2_thuong = calculateElo({ 
        K: user2.K_elo, 
        LPTeam1: allLp2, 
        LPTeam2: allLp1, 
        isWin: !winnerTeams, 
        isMVP: false 
    });
    console.log("Elo2_thuong",Elo2_thuong);

    const updatedListUser1 = ListUser1.map(user => {
        // Find the corresponding player in teams1 to check MVP
        const isMVP = teams1.find(player => player.idUser === user.id)?.idUser === MVPPlayer1;
        let eloChange = isMVP ? Elo1_Mvp : Elo1_thuong;

        if (isNaN(eloChange)) {
            console.error(`Elo change for ${user.name} is NaN`);
            eloChange = 0;
        }
        
        // Get current LP value (handle both LP and Lp properties)
        const currentLP = user.Lp !== undefined ? user.Lp : (user.LP || 0);
        
        // Calculate new LP value
        let newLP = currentLP + eloChange;

        // If current LP is 0 and we're subtracting points, keep it at 0
        // Otherwise, allow subtraction but don't go below 0
        if (currentLP === 0 && eloChange < 0) {
            newLP = 0;
        } else if (newLP < 0) {
            newLP = 0;
        }
        
        // Return updated user with just one LP property
        return { 
            ...user, 
            LP: newLP  // Keep only the DB-style property
        };
    });

    const updatedListUser2 = ListUser2.map(user => {
        // Find the corresponding player in teams2 to check MVP
        const isMVP = teams2.find(player => player.idUser === user.id)?.idUser === MVPPlayer2;
        // If player is MVP in team 2, keep their LP unchanged
        let eloChange = isMVP ? 0 : Elo2_thuong;

        if (isNaN(eloChange)) {
            console.error(`Elo change for ${user.name} is NaN`);
            eloChange = 0;
        }
        
        // Get current LP value (handle both LP and Lp properties)
        const currentLP = user.Lp !== undefined ? user.Lp : (user.LP || 0);
        
        // Calculate new LP value
        let newLP = currentLP + eloChange;

        // If current LP is 0 and we're subtracting points, keep it at 0
        // Otherwise, allow subtraction but don't go below 0
        if (currentLP === 0 && eloChange < 0) {
            newLP = 0;
        } else if (newLP < 0) {
            newLP = 0;
        }


        
        // Return updated user with just one LP property
        return { 
            ...user, 
            LP: newLP  // Keep only the DB-style property
        };
    });

    // Merge both teams and return the list of all users with updated LPs
    return [...updatedListUser1, ...updatedListUser2];
}

module.exports = calculateTeamElo;