/**
 * Calculate ELO change based on match result
 * Formula: ΔELO = K × (S - E) + B
 * Where:
 * - K: Fixed coefficient (max points that can be gained/lost)
 * - S: Actual result (1 for win, 0 for loss)
 * - E: Expected win probability
 * - B: MVP bonus
 * 
 * Expected win probability (E) = 1 / (1 + 10^(D/400))
 * Where D = Opponent MMR - Your MMR
 */
function calculateElo({ 
    K, 
    LPTeam1, 
    LPTeam2, 
    isWin, 
    isMVP 
  }) {
    // Calculate MMR difference (D)
    let D;
    if (LPTeam1 === 0 && LPTeam2 === 0) {
      // Default case when both teams have 0 LP
      D = isWin ? 1 : -1;
    } else {
      D = LPTeam2 - LPTeam1;
    }
  
    // Calculate expected win probability (E)
    const E = 1 / (1 + Math.pow(10, D / 400));
    
    // Actual result (S)
    const S = isWin ? 1 : 0;
  
    // Calculate MVP bonus (B)
    let B = 0;
    if (isMVP) {
      B = isWin ? 5 : Math.floor(Math.random() * 2) + 2; // +5 for win, +2~3 for loss
    }

    // Apply K reduction for high ELO players on loss
    if (!isWin && K > 50) {
      K = Math.floor(K / 2);
    }
  
    // Calculate final ELO change
    const deltaElo = Math.round(K * (S - E) + B);
    return deltaElo;
  }
  
module.exports = calculateElo;