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
                // Remove any existing container with the same name before starting a new one
                sh 'docker rm -f myapp_container || true'
                sh 'docker run -d --name myapp_container myapp:latest'
            }
        }
        stage('Verify Docker Container') {
            steps {
                echo 'Verifying if the Docker container is running...'
                sh 'docker ps'
            }
        }
        stage('Push Docker Image') {
            steps {
                echo 'Pushing Docker image to Docker Hub...'
                withCredentials([usernamePassword(credentialsId: 'c4a4420a-8b6a-43a9-b29c-15a6caf9e741', usernameVariable: 'DOCKER_USERNAME', passwordVariable: 'DOCKER_PASSWORD')]) {
                    sh 'docker login -u $DOCKER_USERNAME -p $DOCKER_PASSWORD'
                    sh 'docker tag myapp:latest skander1f/myapp:latest'  // Tag image with your Docker Hub username
                    sh 'docker push skander1f/myapp:latest'  // Push the image to Docker Hub
                }
            }
        }

    }
}
