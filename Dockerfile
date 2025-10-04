# 1. Chọn base image
FROM openjdk:17-jdk-slim

# 2. Tạo thư mục app trong container
WORKDIR /app

# 3. Copy file JAR đã build vào container
COPY target/backend-0.0.1-SNAPSHOT.jar app.jar

# 4. Expose port của Spring Boot
EXPOSE 8080

# 5. Lệnh chạy ứng dụng
ENTRYPOINT ["java","-jar","app.jar"]
