pipeline {
    agent any
    stages {
        stage('Checkout') {
            steps {
                checkout scm  // Checkout the repository
            }
        }
        stage('MVN Build') {
            steps {
                echo 'Running Maven build...'
                // Run Maven build to generate the JAR file
                sh 'mvn clean install -DskipTests'
            }
        }
        stage('Verify JAR file') {
            steps {
                // Verify that the JAR file has been generated
                sh 'ls -l KADDEM/target/kaddem-0.0.1-SNAPSHOT.jar'
            }
        }
        stage('Build Docker Image') {
            steps {
                echo 'Building Docker image...'
                // Build Docker image after ensuring the JAR file exists
                sh 'docker build -t myapp:latest .'
            }
        }
        stage('Run Docker Container') {
            steps {
                echo 'Running Docker container...'
                // Run Docker container
                sh 'docker run -d --name myapp_container myapp:latest'
            }
        }
    }
}
