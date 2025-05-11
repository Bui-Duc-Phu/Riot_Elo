const { getRandomChampions } = require('./randomChampions');

/**
 * Hàm tạo closure để quản lý danh sách tướng
 * @returns {Object} Object chứa các phương thức để lấy tướng theo vai trò
 */
function createChampionManager() {
    // Biến nhớ trong closure
    let randomChampions = getRandomChampions();

    /**
     * Lấy một tướng ngẫu nhiên theo vai trò
     * @param {string} role - Vai trò của tướng cần lấy
     * @returns {Object|null} Object chứa thông tin tướng hoặc null nếu không còn tướng
     */
    function getChampionByRole(role) {
        // Lọc ra các tướng có vai trò tương ứng
        const championsOfRole = randomChampions.filter(champ => champ.role === role);
        
        if (championsOfRole.length === 0) {
            return null;
        }

        // Chọn ngẫu nhiên một tướng
        const randomIndex = Math.floor(Math.random() * championsOfRole.length);
        const selectedChampion = championsOfRole[randomIndex];

        // Xóa tướng đã chọn khỏi danh sách
        randomChampions = randomChampions.filter(champ => 
            !(champ.name === selectedChampion.name && champ.role === selectedChampion.role)
        );

        return {
            name: selectedChampion.name,
            role: selectedChampion.role
        };
    }

    /**
     * Lấy danh sách tướng còn lại
     * @returns {Array} Danh sách tướng còn lại
     */
    function getRemainingChampions() {
        return [...randomChampions];
    }

    /**
     * Reset lại danh sách tướng
     */
    function resetChampions() {
        randomChampions = getRandomChampions();
    }

    return {
        getChampionByRole,
        getRemainingChampions,
        resetChampions
    };
}

// Tạo instance của champion manager
const championManager = createChampionManager();

module.exports = championManager;

// Test function
function testChampionManager() {
    console.log('=== Bắt đầu test Champion Manager ===');
    
    // Lấy 10 tướng theo thứ tự: Top, Mid, ADC, Jungle, Support, Top, Mid, ADC, Jungle, Support
    const roles = ['Top', 'Mid', 'ADC', 'Jungle', 'Support'];
    
    for (let i = 0; i < 10; i++) {
        const role = roles[i % 5];
        const champion = championManager.getChampionByRole(role);
        console.log(`Lần ${i + 1} - Vai trò ${role}:`, champion);
        
        // In ra danh sách tướng còn lại sau mỗi lần lấy
        console.log('Danh sách tướng còn lại:', championManager.getRemainingChampions());
        console.log('------------------------');
    }
    
    console.log('=== Kết thúc test ===');
}

// Chạy test
// testChampionManager(); 


