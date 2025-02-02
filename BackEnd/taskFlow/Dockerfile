# Etapa 1: Construir a aplicação
FROM maven:latest AS build

# Definir o diretório de trabalho dentro do container
WORKDIR /app

# Copiar o código fonte para o diretório de trabalho
COPY . /app/

# Executar o Maven para construir o projeto
RUN mvn clean package -DskipTests

# Etapa 2: Executar a aplicação
FROM openjdk:20-jdk-slim

# Definir o diretório de trabalho
WORKDIR /app

# Copiar o arquivo JAR do build para o container
COPY --from=build /app/target/taskFlow-0.0.1-SNAPSHOT.jar /app/taskFlow.jar

# Expor a porta do aplicativo
EXPOSE 8080

# Comando para rodar a aplicação
ENTRYPOINT ["java", "-jar", "taskFlow.jar"]
