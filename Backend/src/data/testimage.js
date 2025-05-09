const axios = require('axios');
const cheerio = require('cheerio');

axios.get('https://leagueoflegends.fandom.com/vi/wiki/Aatrox').then(res => {
  const $ = cheerio.load(res.data);
  const imgSrc = $('#mw-content-text > div.mw-content-ltr.mw-parser-output > div:nth-child(2) > div > table > tbody > tr:nth-child(2) > td:nth-child(1) > span > a > img').attr('src');
  console.log(imgSrc);
});
