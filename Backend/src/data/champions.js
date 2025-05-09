const axios = require('axios');
const cheerio = require('cheerio');
const fs = require('fs');

async function scrapeChampions() {
  try {
    // First get the list of all champions from the English wiki as it's more reliable
    const response = await axios.get('https://leagueoflegends.fandom.com/wiki/List_of_champions');
    const $ = cheerio.load(response.data);
    
    const champions = [];
    
    // Find all champion links in the content area
    $('.article-table tbody tr').each((index, element) => {
      const $row = $(element);
      const $nameCell = $row.find('td').first();
      const $link = $nameCell.find('a').first();
      const $img = $nameCell.find('img').first();
      
      const championName = $link.attr('title')?.replace('/LoL', '');
      const championImage = $img.attr('data-src') || $img.attr('src');
      
      if (championName && championImage && !championImage.includes('data:image/gif')) {
        champions.push({
          name: championName,
          imageUrl: championImage
        });
      }
    });

    console.log(`Found ${champions.length} champions`);
    
    // Save to JSON file
    fs.writeFileSync('champions.json', JSON.stringify(champions, null, 2));
    console.log('Champion data has been saved to champions.json');
    
  } catch (error) {
    console.error('Error scraping champion data:', error);
  }
}

scrapeChampions(); 