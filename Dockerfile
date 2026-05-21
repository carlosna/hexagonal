# =============================================================================
# Dockerfile - boletojuros application (Spring Boot / Java 21)
# =============================================================================
# Place this file at the root of carlosna/hexagonal.
# It expects the Maven project to live in ./boletojuros.
# =============================================================================

FROM eclipse-temurin:21-jdk AS build

WORKDIR /app

COPY boletojuros/mvnw .
COPY boletojuros/mvnw.cmd .
COPY boletojuros/.mvn .mvn
COPY boletojuros/pom.xml .

RUN chmod +x mvnw
RUN ./mvnw dependency:go-offline -B

COPY boletojuros/src ./src

RUN ./mvnw package -DskipTests -B && \
    mv target/*.jar target/app.jar

FROM eclipse-temurin:21-jre AS runtime

LABEL maintainer="Carlos Nascimento <carlosna>"
LABEL description="boletojuros application"
LABEL org.opencontainers.image.source="https://github.com/carlosna/hexagonal"

RUN apt-get update && \
    apt-get install -y --no-install-recommends curl && \
    rm -rf /var/lib/apt/lists/* && \
    groupadd -r appuser && \
    useradd -r -g appuser -d /app -s /sbin/nologin appuser

WORKDIR /app

COPY --from=build --chown=appuser:appuser /app/target/app.jar ./app.jar

EXPOSE 8080

USER appuser

HEALTHCHECK --interval=30s --timeout=3s --start-period=40s --retries=3 \
  CMD curl -f http://localhost:8080/actuator/health || exit 1

ENV JAVA_OPTS="-XX:+UseContainerSupport -XX:MaxRAMPercentage=75.0 -Djava.security.egd=file:/dev/./urandom"

ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]
