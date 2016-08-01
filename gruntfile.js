module.exports = function(grunt){

    grunt.loadNpmTasks('grunt-contrib-uglify');

    grunt.initConfig({
     uglify: {
         options: {
               sourceMap: false,
               wrap: 'exports',
               mangle: false
         },
         my_target: {
           files: {
             'src/main/webapp/resources/js/start.bundle.min.js': ['src/main/webapp/resources/js/start.bundle.js'],
             'src/main/webapp/resources/js/refreshboard.bundle.min.js': ['src/main/webapp/resources/js/refreshboard.bundle.js'],
             'src/main/webapp/resources/js/loginuser.bundle.min.js': ['src/main/webapp/resources/js/loginuser.bundle.js'],
             'src/main/webapp/resources/js/sessionpicker.bundle.min.js': ['src/main/webapp/resources/js/sessionpicker.bundle.js']
           }
         }
       }
    });

    grunt.registerTask('default', ['uglify']);
}
