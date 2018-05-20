const fs = require('fs');
const path = require('path');
const CodeGen = require('swagger-typescript-codegen').CodeGen;

console.info('Generating API client from OpenAPI specification...');

const specification = JSON.parse(fs.readFileSync(path.resolve(__dirname, 'src/api/bff-api.json'), 'utf8'));

const generatedCode = CodeGen.getTypescriptCode({
  className: 'BFFClient',
  swagger: specification,
  template: {
    method: fs.readFileSync(path.resolve(__dirname, 'src/api/method.mustache'), 'utf8')
  },
  mustache: {
    basePath: specification.basePath
  }
});

fs.writeFileSync(path.resolve(__dirname, 'src/api/BFFClient.ts'), generatedCode, 'utf8');

console.info('API client successfully generated.');
