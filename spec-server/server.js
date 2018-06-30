const fs = require('fs');
const express = require('express');
const mustacheExpress = require('mustache-express');
const pathToSwaggerUi = require('swagger-ui-dist').absolutePath();

// Determine the list of OpenAPI specification files in the "specs" subdirectory.
const specsList = fs.readdirSync(__dirname + '/specs');

// Build an array of Swagger UI specification references to allow selection of the specs via dropdown.
const specsConfig = specsList.map((specFilename) => ({
  url: 'specs/' + specFilename,
  name: specFilename.replace(/\.[^\.]+$/, '')
}));

// Create the router serving the main page.
const indexRouter = express.Router();

// Render function using the template "views/index.mustache" to render the main page.
function renderIndexPage(req, res) {
  res.render('index', {
    title: 'OpenAPI Contract Example Swagger UI',
    specs: JSON.stringify(specsConfig)
  });
}

// Use the render function for both the root path and as override for "index.html".
indexRouter.get('/', renderIndexPage);
indexRouter.get('/index.html', renderIndexPage);

// Create the Express server.
const server = express();

// Configure the Mustache template engine.
server.engine('mustache', mustacheExpress());
server.set('view engine', 'mustache');
server.set('views', __dirname + '/views');

// Mount the router serving the main page.
server.use(indexRouter);

// Mount the Swagger UI static resources.
server.use(express.static(pathToSwaggerUi));

// Mount the OpenAPI specifications from the "specs" subdirectory.
server.use('/specs', express.static(__dirname + '/specs'));

// Run the server.
server.listen(3000, () => {
  console.info('Specs server is listening on port 3000');
});
