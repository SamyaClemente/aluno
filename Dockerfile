# Use a imagem do OpenJDK 17 como base
FROM openjdk:17-jdk-slim

# Defina o diretório de trabalho como /app
WORKDIR /app

# Copie todos os arquivos JAR presentes no diretório target para o diretório /app na imagem
COPY target/*.jar /app/

# Expõe a porta 8080 para acesso externo
EXPOSE 8080

# Comando para executar a aplicação quando o container for iniciado
CMD java -jar $(find . -maxdepth 1 -name "*.jar")
