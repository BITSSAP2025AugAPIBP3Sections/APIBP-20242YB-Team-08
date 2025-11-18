const fs = require('fs');
const path = require('path');
module.exports = {
  init: function (context, events, done) {
    try {
      const yaml = require('js-yaml');
      const file = path.join(__dirname, '..', 'config', 'secrets.yml');
      const data = yaml.load(fs.readFileSync(file, 'utf8'));
      context.vars.token = data.token;
    } catch (e) {
      context.vars.token = "";
    }
    return done();
  }
};
