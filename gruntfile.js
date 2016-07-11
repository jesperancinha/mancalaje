module.exports = function(grunt){
	grunt.initConfig({

        pkg: grunt.file.readJSON('package.json'),
        cssmin: {
             options: {
             }, target: {
                    files: {
                        'output.css' : [ 'style.css', 'reset.css']
                    }
             }
        }
   });
   grunt.loadNpmTasks('grunt-contrib-cssmin');
   grunt.registerTask('default', ['cssmin'])
};
