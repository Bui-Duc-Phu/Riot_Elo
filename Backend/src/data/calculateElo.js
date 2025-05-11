function calculateElo({ 
    K, 
    LPTeam1, 
    LPTeam2, 
    isWin, 
    isMVP 
  }) {

    let D;

    // RÀNG BUỘC: Nếu LP = 0 ở cả 2 đội
    if (LPTeam1 === 0 && LPTeam2 === 0) {
      D = isWin ? 1 : -1;
    } else {
      D = LPTeam2 - LPTeam1;
    }
  
    const E = 1 / (1 + Math.pow(10, D / 400));
    const S = isWin ? 1 : 0;
  
    // MVP bonus
    let B = 0;
    if (isMVP) {
      B = isWin ? 5 : Math.floor(Math.random() * 2) + 2;
    }

    // Nếu thuộc team thua và K_elo > 50, giảm K_elo xuống 1/3
    if (!isWin && K > 50) {
      K = Math.floor(K / 3);
    }
  
    const deltaElo = Math.round(K * (S - E) + B);
    return deltaElo;
  }
  
module.exports = calculateElo;