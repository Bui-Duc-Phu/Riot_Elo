const champions = require('../data/champions.json');

/**
 * Hàm bốc ngẫu nhiên 10 tướng với mỗi vai trò có đúng 2 tướng
 * @returns {Array} Mảng chứa 10 object, mỗi object có dạng {name: string, role: string}
 */
function getRandomChampions() {
    const roles = ['Top', 'Mid', 'ADC', 'Jungle', 'Support'];
    const selectedChampions = [];

    // Lấy 2 tướng cho mỗi vai trò
    roles.forEach(role => {
        // Lọc ra các tướng có vai trò tương ứng
        const championsOfRole = champions.filter(champ => champ.role === role);
        
        // Lấy 2 tướng ngẫu nhiên cho vai trò này
        for (let i = 0; i < 2; i++) {
            const randomIndex = Math.floor(Math.random() * championsOfRole.length);
            const selectedChampion = championsOfRole[randomIndex];
            
            selectedChampions.push({
                name: selectedChampion.name,
                role: selectedChampion.role
            });

            // Xóa tướng đã chọn để tránh chọn trùng
            championsOfRole.splice(randomIndex, 1);
        }
    });

    // Xáo trộn thứ tự các tướng để không bị sắp xếp theo vai trò
    return selectedChampions.sort(() => Math.random() - 0.5);
}

module.exports = {
    getRandomChampions
};

console.log(getRandomChampions());

