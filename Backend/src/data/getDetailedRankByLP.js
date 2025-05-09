/**
 * Chuyển số (1–5) sang La Mã (I, II, III, IV, V)
 */
function toRoman(num) {
    const romans = ["","I","II","III","IV","V"];
    return romans[num] || "";
  }
  
  /**
   * Trả về rank + tier (La Mã):
   * - Đồng V → Đồng I (0–99 LP, mỗi bậc 20 LP)
   * - Bạc IV → Bạc I (100–299 LP, mỗi bậc 50 LP)
   * - Vàng IV → Vàng I (300–599 LP, mỗi bậc 75 LP)
   * - Bạch Kim IV → Bạch Kim I (600–999 LP, mỗi bậc 100 LP)
   * - Kim Cương IV → Kim Cương I (1000–1399 LP, mỗi bậc 100 LP)
   * - Cao Thủ, Đại Cao Thủ, Thách Đấu (không phân bậc)
   */
  function getDetailedRankByLP(lp) {
    let tier, roman;
    if (lp < 0) lp = 0;
    
    if (lp < 100) {
      // Đồng V→I
      tier = 5 - Math.floor(lp / 20);
      return `Đồng ${toRoman(tier)}`;
    }
    
    if (lp < 300) {
      // Bạc IV→I
      tier = 4 - Math.floor((lp - 100) / 50);
      return `Bạc ${toRoman(tier)}`;
    }
    
    if (lp < 600) {
      // Vàng IV→I
      tier = 4 - Math.floor((lp - 300) / 75);
      return `Vàng ${toRoman(tier)}`;
    }
    
    if (lp < 1000) {
      // Bạch Kim IV→I
      tier = 4 - Math.floor((lp - 600) / 100);
      return `Bạch Kim ${toRoman(tier)}`;
    }
    
    if (lp < 1400) {
      // Kim Cương IV→I
      tier = 4 - Math.floor((lp - 1000) / 100);
      return `Kim Cương ${toRoman(tier)}`;
    }
    
    if (lp < 1700)  return "Cao Thủ";
    if (lp < 2000)  return "Đại Cao Thủ";
    return "Thách Đấu";
  }
  
