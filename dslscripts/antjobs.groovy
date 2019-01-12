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
             shell 'sh -p \'123456\' scp /Users/Shared/Jenkins/Home/workspace/antbuild/build/demoapp-dev.war release@10.12.108.11:/opt/tomcat/webapps/'
      }
}
