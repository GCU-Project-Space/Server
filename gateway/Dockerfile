# 1. OpenJDK 이미지를 베이스로 사용
FROM openjdk:21-jdk-slim

# 2. jar 파일 복사
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} app.jar

# 3. 실행
ENTRYPOINT ["java","-jar","app.jar"]