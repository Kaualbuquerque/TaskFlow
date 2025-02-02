# Etapa 1: Construir a aplicação
FROM maven:3.8.6-openjdk-21-slim AS build

# Definir o diretório de trabalho dentro do container
WORKDIR /app

# Copiar o arquivo pom.xml para resolver as dependências
COPY pom.xml /app/

# Baixar as dependências (sem copiar o código ainda)
RUN mvn dependency:go-offline

# Copiar o código-fonte para o container
COPY src /app/src/

# Construir o projeto com Maven
RUN mvn clean package -DskipTests

# Etapa 2: Executar a aplicação
FROM openjdk:21-jdk-slim

# Definir o diretório de trabalho dentro do container
WORKDIR /app

# Copiar o arquivo JAR gerado na etapa de build
COPY --from=build /app/target/taskFlow-0.0.1-SNAPSHOT.jar /app/taskFlow.jar

# Expor a porta onde a aplicação será executada (padrão para Spring Boot é 8080)
EXPOSE 8080

# Comando para executar a aplicação
CMD ["java", "-jar", "taskFlow.jar"]
