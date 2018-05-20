const fs = require('fs');
const path = require('path');
const CodeGen = require('swagger-typescript-codegen').CodeGen;

console.info('Generating API client from OpenAPI specification...');

const openAPISpec = JSON.parse(fs.readFileSync(path.resolve(__dirname, 'src/api/bff-api.json'), 'utf8'));

const clientTypeScriptCode = CodeGen.getTypescriptCode({
  className: 'BFFClient',
  swagger: openAPISpec
});

fs.writeFileSync(path.resolve(__dirname, 'src/api/BFFClient.ts'), clientTypeScriptCode, 'utf8');

console.info('API client successfully generated.');
