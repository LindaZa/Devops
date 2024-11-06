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
                sh 'sudo -S docker build -t myapp:latest .'  // Replace "myapp" with your desired image name
            }
        }
        stage('Run Docker Container') {
            steps {
                sh 'sudo -S docker run -d --name myapp_container myapp:latest'  // Replace "myapp_container" as needed
            }
        }
    }
}
