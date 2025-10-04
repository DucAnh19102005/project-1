# -------------------
# Stage 1: Build
# -------------------
FROM maven:3.9.2-eclipse-temurin-17 AS build

# Thiết lập thư mục làm việc
WORKDIR /app

# Copy pom.xml và source code
COPY pom.xml .
COPY src ./src

# Build project và tạo JAR
RUN mvn clean package -DskipTests

# -------------------
# Stage 2: Runtime
# -------------------
FROM openjdk:17-jdk-slim

# Thiết lập thư mục làm việc
WORKDIR /app

# Copy JAR từ stage build
COPY --from=build /app/target/backend-0.0.1-SNAPSHOT.jar app.jar

# Expose port Spring Boot
EXPOSE 8080

# Lệnh chạy ứng dụng
ENTRYPOINT ["java","-jar","app.jar"]
