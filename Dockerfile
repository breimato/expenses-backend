# Build from monorepo root:
#   docker build -f backend/Dockerfile -t expenses-backend .
#
# Run (example, matching docker-compose postgres):
#   docker run --rm -p 8080:8080 \
#     -e DB_HOST=host.docker.internal \
#     -e DB_PORT=5433 \
#     -e DB_NAME=expenses_db \
#     -e DB_USERNAME=expenses_user \
#     -e DB_PASSWORD=expenses_pass \
#     expenses-backend

# ---- build ----
FROM eclipse-temurin:21-jdk-alpine AS build

WORKDIR /workspace

COPY mvnw mvnw.cmd pom.xml ./
COPY .mvn .mvn
COPY backend backend

RUN chmod +x mvnw \
    && ./mvnw -pl backend -am -DskipTests package

# ---- runtime ----
FROM eclipse-temurin:21-jre-alpine

WORKDIR /app

RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring

COPY --from=build /workspace/backend/target/expenses-*.jar /app/app.jar

EXPOSE 8080

ENV JAVA_OPTS=""

ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar /app/app.jar"]
