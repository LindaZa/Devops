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
                sh 'sudo docker build -t myapp:latest .'
            }
        }
        stage('Run Docker Container') {
            steps {
                sh 'docker run -d --name myapp_container myapp:latest'
            }
        }

    }
}
