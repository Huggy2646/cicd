pipeline {
    agent any
    stages {
        stage('Checkout') {
            steps {
                git 'https://github.com/your-repo/your-project.git'
            }
        }
        stage('Build') {
            steps {
                script {
                    def gradleHome = tool name: 'Gradle', type: 'gradle'
                    withEnv(["PATH+GRADLE=${gradleHome}/bin"]) {
                        sh './gradlew bootJar'
                    }
                }
            }
        }
        stage('Archive') {
            steps {
                archiveArtifacts artifacts: '**/build/libs/*.jar', allowEmptyArchive: true
            }
        }
    }
}
