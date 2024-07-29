pipeline {
    agent any  // Jenkins 에이전트 설정
    stages {
        stage('Checkout') {
            steps {
                // 소스 코드 체크아웃
                git 'https://github.com/your-repo/your-project.git'
            }
        }
        stage('Build') {
            steps {
                // Gradle 빌드 수행
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
                // JAR 파일 아티팩트로 보관
                archiveArtifacts artifacts: '**/build/libs/*.jar', allowEmptyArchive: true
            }
        }
    }
}
