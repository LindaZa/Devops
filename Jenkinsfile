pipeline {
    agent any
    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }
        stage('MVN Build') {
            steps {
                echo 'Running Maven build...'
                sh 'mvn install -DskipTests'
            }
        }
        stage('Build Docker Image') {
            steps {
                echo 'Building Docker image...'
                sh 'docker build -t myapp:latest .'
            }
        }
        stage('Run Docker Container') {
            steps {
                echo 'Running Docker container...'
                sh 'docker run -d --name myapp_container myapp:latest'
            }
        }
    }
}
