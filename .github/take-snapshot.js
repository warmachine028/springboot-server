const puppeteer = require('puppeteer');
const fs = require('fs');

const takeSnapShot = async () => {
  const url = 'https://warmachine028.github.io/springboot-server/client';  // Replace with your actual deployed URL
  const browser = await puppeteer.launch();
  const page = await browser.newPage();
  await page.goto(url);
  await page.screenshot({ path: 'snapshot.png' });
  await browser.close();
};

takeSnapShot();