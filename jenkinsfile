pipeline {
    agent any

    environment {
        DOCKER_COMPOSE_FILE = 'docker-compose.yml'
    }

    stages {
        stage('Checkout') {
            steps {
                echo 'Cloning the repo...'
                git 'https://github.com/V-Pavithra/Ecommerce-DevOpsProject.git'
            }
        }

        stage('Build with Maven') {
            steps {
                sh 'mvn clean install -DskipTests'
            }
        }

        stage('Build Docker Images') {
            steps {
                sh 'docker compose build'
            }
        }

        stage('Deploy with Docker Compose') {
            steps {
                sh 'docker compose down || true'  // Clean existing containers
                sh 'docker compose up -d'         // Deploy fresh containers
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