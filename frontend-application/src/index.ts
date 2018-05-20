import BFFClient from './api/BFFClient';

// Assuming that BFF is accessible within the same domain as the frontend at the path "/api".
const bff = new BFFClient('/api');

document.querySelector('#load-button').addEventListener('click', (event) => {
  bff.getMessage({}).then(response => {
    document.querySelector('#message-text').innerHTML = response.body.text;
  }).catch(error => {
    console.error('Error loading message!', error);
    document.querySelector('#message-text').innerHTML = 'Ooops! Something went wrong! See the console.';
  });
});
