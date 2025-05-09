const genMatches = require('./genMatches');
const calculateTeamElo = require('./calculateTeamElo');
async function testGenMatches(jsonUsers) {
    try {
        const idUsers = JSON.parse(jsonUsers);
        if (!Array.isArray(idUsers) || idUsers.length < 10) {
            throw new Error("Input JSON must be an array with at least 10 user IDs");
        }

        const userMain = idUsers[Math.floor(Math.random() * idUsers.length)];

        console.log(`Testing genMatches with userMain: ${userMain}`);

        const { teams1Obj, teams2Obj, winnerTeams, MVPPlayer1, MVPPlayer2 } = genMatches(idUsers, userMain);

        // ✅ Thêm await ở đây
        const listUser = await calculateTeamElo(teams1Obj, teams2Obj, winnerTeams, MVPPlayer1, MVPPlayer2);
        console.log("listUser",listUser);

        return;

    } catch (error) {
        console.error("Test failed:", error.message);
        throw error;
    }
}

// ✅ Gọi function async
const tempJson = JSON.stringify([1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12]);
testGenMatches(tempJson);

module.exports = testGenMatches;
