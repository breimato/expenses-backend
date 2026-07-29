# Build from this directory (backend/):
#   docker build -t expenses-backend .
#
# Run (example):
#   docker run --rm -p 8080:8080 \
#     -e DB_HOST=host.docker.internal \
#     -e DB_PORT=5433 \
#     -e DB_NAME=expenses_db \
#     -e DB_USERNAME=expenses_user \
#     -e DB_PASSWORD=expenses_pass \
#     expenses-backend

# ---- build ----
FROM maven:3.9.9-eclipse-temurin-21-alpine AS build

WORKDIR /workspace

COPY pom.xml ./
COPY src ./src

RUN mvn -DskipTests package \
    && cp target/expenses-*.jar /workspace/app.jar

# ---- runtime ----
FROM eclipse-temurin:21-jre-alpine

WORKDIR /app

RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring

COPY --from=build /workspace/app.jar /app/app.jar

EXPOSE 8080

ENV JAVA_OPTS=""

ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar /app/app.jar"]
