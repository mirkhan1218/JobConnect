# Build Stage
# Amazoncorretto:21.0.3 이미지를 사용하여 빌드
FROM amazoncorretto:21.0.3 as build
# 작업 디렉토리를 /app으로 설정
WORKDIR /app
# 의존성을 먼저 복사하고 다운로드하여 캐시 활용
COPY build.gradle settings.gradle gradlew ./
COPY gradle ./gradle
COPY src ./src
# 파일을 컨테이너로 복사
# COPY . .
# Gradle 빌드 명령 실행
RUN ./gradlew build --no-daemon

# Run Stage
# Amazoncorretto:21.0.3 이미지를 사용하여 런타임 설정
FROM amazoncorretto:21.0.3
# 작업 디렉토리를 /app으로 설정
WORKDIR /app
# ARG 명령어로 JAR 파일 경로 설정
ARG JAR_FILE=build/libs/*.jar
# 빌드된 JAR 파일을 복사
COPY ${JAR_FILE} app.jar
# 8080번 포트 노출
EXPOSE 8080
# Java 애플리케이션 실행
ENTRYPOINT ["java", "-jar", "/app/app.jar"]