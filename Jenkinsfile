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
        stage('Verify JAR file') {
            steps {
                echo 'Verifying if the JAR file exists...'
                sh 'ls -l target/kaddem-0.0.1-SNAPSHOT.jar'  // Ensure the file exists
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
