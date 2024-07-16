import puppeteer from 'puppeteer';
const sleep = ms => new Promise(res => setTimeout(res, ms));

const takeSnapShot = async () => {
  const url = 'https://warmachine028.github.io/springboot-server/client';  // Replace with your actual deployed URL
  const browser = await puppeteer.launch();
  const page = await browser.newPage();
  await page.setViewport({ width: 1920, height: 1080 });
  await page.goto(url);
  await sleep(3000);
  await page.screenshot({ path: '.github/preview.png', fullPage: true, captureBeyondViewport: true });
  await browser.close();
};

takeSnapShot();
