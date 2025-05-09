function genMatches(idUsers, userMain) {
    // Validate inputs
    if (!idUsers.includes(userMain)) {
        throw new Error("userMain must be in idUsers list");
    }

    // Helper function to get random integer in range [min, max]
    function getRandomInt(min, max) {
        return Math.floor(Math.random() * (max - min + 1)) + min;
    }

    // Helper function to shuffle array
    function shuffleArray(array) {
        for (let i = array.length - 1; i > 0; i--) {
            const j = Math.floor(Math.random() * (i + 1));
            [array[i], array[j]] = [array[j], array[i]];
        }
        return array;
    }

    // Select 9 random users (excluding userMain) + userMain = 10 members
    const otherUsers = idUsers.filter(id => id !== userMain);
    const shuffledOthers = shuffleArray([...otherUsers]).slice(0, 9);
    const allMembers = [userMain, ...shuffledOthers];

    // Split into Teams1 and Teams2 (5 users each)
    const shuffledMembers = shuffleArray([...allMembers]);
    const teams1 = shuffledMembers.slice(0, 5);
    const teams2 = shuffledMembers.slice(5, 10);

    // Determine winner randomly
    const winnerTeams = Math.random() < 0.5;

    // Generate team stats
    const allGold1 = getRandomInt(15000, 20000);
    const allGold2 = getRandomInt(15000, 20000);

    const allKill1 = getRandomInt(10, 50);
    const allKill2 = winnerTeams 
        ? getRandomInt(10, allKill1 - 1) // team1 wins, so team2 has fewer kills
        : getRandomInt(allKill1 + 1, 50); // team2 wins, so team2 has more kills

    const allDeath2 = allKill1 + getRandomInt(0, 3);
    const allDeath1 = allKill2 + getRandomInt(0, 3);

    const allAssists1 = Math.floor(0.9 * allKill1);
    const allAssists2 = Math.floor(0.9 * allKill2);

    // Generate random positions for each team (no duplicates)
    const positions = ['Ad', 'Mid', 'Top', 'Support', 'Jung'];
    const position1 = shuffleArray([...positions]);
    const position2 = shuffleArray([...positions]);

    // Generate key member contributions (acen values sum to 0.9 for first 4 members)
    function generateAcenValues() {
        // Valid combinations of 4 values that sum to 0.9
        const validCombinations = [
            [0.3, 0.2, 0.2, 0.2],
            [0.4, 0.2, 0.2, 0.1],
            [0.5, 0.2, 0.1, 0.1],
            [0.6, 0.1, 0.1, 0.1],
            [0.3, 0.3, 0.2, 0.1],
            [0.4, 0.3, 0.1, 0.1]
        ];
        // Randomly select a combination
        const selected = validCombinations[Math.floor(Math.random() * validCombinations.length)];
        // Shuffle the combination to randomize order
        return shuffleArray([...selected]);
    }

    const acen1 = generateAcenValues();
    const acen2 = generateAcenValues();
    const MVP1 = Math.max(...acen1); // Giá trị lớn nhất của acen1
    const MPV2 = Math.max(...acen2); 
    console.log("acen1", acen1);
    console.log("acen2", acen2);

    // Find players with highest ACEN scores
    const getMVPPlayers = () => {
        const team1MVPIndex = acen1.indexOf(MVP1);
        const team2MVPIndex = acen2.indexOf(MPV2);
        return {
            team1MVP: teams1[team1MVPIndex],
            team2MVP: teams2[team2MVPIndex]
        };
    };

    const mvpPlayers = getMVPPlayers();

    // Calculate stats for Teams1
    const teams1Obj = teams1.map((idUser, index) => {
        const isLast = index === 4;
        const acen = isLast ? 1 - acen1.slice(0, 4).reduce((a, b) => a + b, 0) : acen1[index];
        return {
            idUser,
            Gold: Math.floor(acen * allGold1),
            Kills: Math.floor(acen * allKill1),
            Death: Math.floor(acen * allDeath1),
            Assists: Math.floor(acen * allAssists1),
            Position: position1[index]
        };
    });

    // Calculate stats for Teams2
    const teams2Obj = teams2.map((idUser, index) => {
        const isLast = index === 4;
        const acen = isLast ? 1 - acen2.slice(0, 4).reduce((a, b) => a + b, 0) : acen2[index];
        return {
             idUser,
            gold: Math.floor(acen * allGold2),
            kills: Math.floor(acen * allKill2),
            death: Math.floor(acen * allDeath2),
            assists: Math.floor(acen * allAssists2),
            position: position2[index]
        };
    });

    // Log the results
    console.log("Teams1:", teams1Obj);
    console.log("Teams2:", teams2Obj);
  
    console.log("MVP1:", mvpPlayers.team1MVP);
    console.log("MPV2:", mvpPlayers.team2MVP);

    let MVPPlayer1 = mvpPlayers.team1MVP;
    let MVPPlayer2 = mvpPlayers.team2MVP;

    return { teams1Obj, teams2Obj, winnerTeams,MVPPlayer1,MVPPlayer2};
}

module.exports = genMatches;