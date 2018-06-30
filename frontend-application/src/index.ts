import { MessagesApi } from './api/bff-api-client';

// Assuming that BFF is accessible within the same domain as the frontend under the path "/api/v1".
const messagesApi = new MessagesApi(null, '/api/v1');

document.querySelector('#load-button').addEventListener('click', (event) => {
  messagesApi.getMessage().then(response => {
    document.querySelector('#message-text').innerHTML = response.text;
  }).catch(error => {
    console.error('Error loading message!', error);
    document.querySelector('#message-text').innerHTML = 'Ooops! Something went wrong! See the console.';
  });
});
