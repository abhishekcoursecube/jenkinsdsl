job('antbuild') {
    scm {
        github('tetradev01/demoapp', 'master')
    }

    steps {
        ant {
            prop('version', 'dev')
            buildFile('build.xml')
            antInstallation('Ant 1.9.3')
        }
    }

    publishers {
        downstream 'deploy', 'SUCCESS'
    }
}

job('deploy') {
    description 'Deploy app to the demo application server'
    /*
     * configuring ssh plugin to run docker commands
     */
    steps{
             shell 'sudo -t scp /Users/Shared/Jenkins/Home/workspace/antbuild/build/demoapp-dev.war /users/abhishekotta/Documents/JenkinsDemo/apache-tomcat-8.5.37/webapps'
      }
}
