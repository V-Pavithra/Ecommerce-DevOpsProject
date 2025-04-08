pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                echo 'Cloning the repo...'
                git url: 'https://github.com/V-Pavithra/Ecommerce-DevOpsProject.git', branch: 'main'
            }
        }

        stage('Debug Info') {
            steps {
                sh 'pwd'
                sh 'ls -la'
                sh 'which docker-compose || echo "docker-compose not in PATH"'
            }
        }

        stage('Build with Maven') {
            steps {
                sh 'mvn clean install -DskipTests'
            }
        }

        stage('Build Docker Images') {
            steps {
                // Try with full path to docker-compose
                sh '''
                export PATH=$PATH:/usr/bin:/usr/local/bin
                /usr/bin/docker-compose build || docker-compose build
                '''
            }
        }

        stage('Deploy with Docker Compose') {
            steps {
                sh '''
                export PATH=$PATH:/usr/bin:/usr/local/bin
                /usr/bin/docker-compose down || true
                /usr/bin/docker-compose up -d
                '''
            }
        }
    }

    post {
        always {
            echo 'Pipeline execution complete.'
        }
        failure {
            echo 'Pipeline failed. Check logs for details'
        }
    }
}