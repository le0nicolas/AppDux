# Imagen base de OpenJDK
FROM openjdk:17-jdk-alpine

# Directorio de trabajo en /app
WORKDIR /app

# Se copia el archivo JAR generado por Maven a la imagen
COPY target/pruebaDux-0.0.1-SNAPSHOT.jar app.jar

# Exponemos el puerto 8080 en la imagen
EXPOSE 8080

# Comando para ejecutar la aplicación Spring Boot cuando el contenedor se inicie
CMD ["java", "-jar", "app.jar"]

#Para ejecutar la imagen
#docker build -t appdux .
#docker run -p 8080:8080 appdux