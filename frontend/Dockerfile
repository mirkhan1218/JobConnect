# Build Stage
# Node.js 20.15.0 이미지를 사용하여 빌드
FROM node:20.15 AS build
# 작업 디렉토리를 /app로 설정
WORKDIR /app
# package.json 파일을 컨테이너로 복사
COPY package.json ./
# 의존성 설치
RUN npm install
# 모든 파일을 컨테이너로 복사
COPY . .
# 빌드 명령 실행
RUN npm run build

# Run Stage
# Nginx 1.27.0 이미지를 사용하여 런타임 설정
FROM nginx:1.27.0
# 빌드된 파일을 Nginx HTML 디렉토리로 복사
COPY --from=build /app/build /usr/share/nginx/html
# 3000번 포트 노출
EXPOSE 3000
# Nginx 실행
CMD ["nginx", "-g", "daemon off;"]
