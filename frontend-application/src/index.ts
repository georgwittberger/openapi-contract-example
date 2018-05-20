import BFFClient from './api/BFFClient';

const bff = new BFFClient('/api/v1');

document.querySelector('#load-button').addEventListener('click', (event) => {
  bff.getMessage({}).then(response => {
    document.querySelector('#message-text').innerHTML = response.body.text;
  }).catch(error => {
    console.error('Error loading message!', error);
    document.querySelector('#message-text').innerHTML = 'Ooops! Something went wrong! See the console.';
  });
});
