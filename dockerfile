# Utiliser l'image openjdk 17 depuis Docker Hub
FROM openjdk:17

# Exposer le port sur lequel l'application Spring Boot va tourner
EXPOSE 8080

# Copier le fichier JAR dans l'image Docker
ADD target/kaddem-0.0.1-20241104.212128-1.jar /kaddem-0.0.1.jar

# Définir la commande pour exécuter l'application Spring Boot
ENTRYPOINT ["java", "-jar", "/kaddem-0.0.1.jar"]
