# =============================================================================
# Dockerfile - boletojuros application (Spring Boot / Java 21)
# =============================================================================

FROM maven:3.9.9-eclipse-temurin-21 AS build

WORKDIR /workspace

COPY pom.xml pom.xml

RUN mvn dependency:go-offline -B

COPY src src

RUN mvn package -DskipTests -B && \
    cp target/*.jar target/app.jar

FROM eclipse-temurin:21-jre AS runtime

LABEL maintainer="Carlos Nascimento <carlosna>"
LABEL description="boletojuros application"
LABEL org.opencontainers.image.source="https://github.com/carlosna/hexagonal"

RUN apt-get update && \
    apt-get install -y --no-install-recommends curl && \
    rm -rf /var/lib/apt/lists/* && \
    groupadd --system appuser && \
    useradd --system --gid appuser --home-dir /app --shell /usr/sbin/nologin appuser

WORKDIR /app

COPY --from=build --chown=appuser:appuser /workspace/target/app.jar app.jar

EXPOSE 8080

USER appuser

ENV JAVA_OPTS="-XX:+UseContainerSupport -XX:MaxRAMPercentage=75.0 -Djava.security.egd=file:/dev/./urandom"

HEALTHCHECK --interval=30s --timeout=3s --start-period=40s --retries=3 \
  CMD curl -fsS http://localhost:8080/actuator/health || exit 1

ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]
