pipeline {
    agent any  // Jenkins 에이전트 설정
    stages {
        stage('Checkout') {
            steps {
                // 소스 코드 체크아웃
                git branch: 'main', url: 'https://github.com/Huggy2646/cicd.git'
            }
        }
 stage('Build') {
            steps {
                script {
                    // Gradle Wrapper의 실행 권한을 다시 설정 (옵션)
                    sh 'chmod 755 gradlew'
                    // Gradle Wrapper를 사용하여 빌드
                    sh './gradlew bootJar'
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