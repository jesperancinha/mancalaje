module.exports = {
     entry: { sessionpicker: './sourcefiles/sessionpicker.js',
              loginuser: './sourcefiles/loginuser.js',
              refreshboard:'./sourcefiles/refreshboard.js',
              start: './sourcefiles/start.js'
             },
     output: {
         path: './src/main/webapp/resources/js',
         filename: '[name].bundle.js'
     }
 };