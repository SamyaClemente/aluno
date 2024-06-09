# Use a imagem do OpenJDK 17 como base
FROM openjdk:17-jdk-slim

# Defina o diretório de trabalho como /app
WORKDIR /app

# Copie o jar da sua aplicação para o diretório /app na imagem
COPY target/aluno-0.0.1-SNAPSHOT.jar /app/aluno-0.0.1-SNAPSHOT.jar

# Expõe a porta 8080 para acesso externo
EXPOSE 8080

# Comando para executar a aplicação quando o container for iniciado
CMD ["java", "-jar", "aluno-0.0.1-SNAPSHOT.jar"]
