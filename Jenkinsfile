pipeline {
    agent any
    tools { 
      maven 'MAVEN_HOME' 
      jdk 'JAVA_HOME' 
    }
    stages {
        stage('Set Release Version') {
            steps {
                script {
                    env.version = sh script: "mvn help:evaluate -Dexpression=project.version | grep -Pv '(\\[|Download|Progress)'", returnStdout: true
                    env.releaseVersion =  input message: 'What is the new release version?',
                                                parameters: [string(defaultValue: version,
                                                description: '',
                                                name: 'Release Version')]
                }
            }
        }
        stage("Set New Develop Version") {
            steps {
                script {
                    env.developmentVersion =  input message: 'What is the new development version?',
                                                parameters: [string(defaultValue: version,
                                                description: '',
                                                name: 'Development Version')]
                }
            }
        }
        stage('Preparing release') {
            steps {
                dir('mito') {
                    sh "mvn -DdryRun release:prepare -DautoVersionSubmodules=true -DreleaseVersion=\"${env.releaseVersion}\" -DdevelopmentVersion=\"${env.developmentVersion}\" -DignoreSnapshots=true"
                }
            }
        }
    }
}
