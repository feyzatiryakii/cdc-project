
FROM maven:3.9.6-eclipse-temurin-17 AS builder

WORKDIR /app

#pom.xmli ayrı kaynak kodu ayrı kopyaladım ayrı katmanlarda kalsınlar cachelesin
COPY pom.xml .
#bağımlılıkları önceden indir
RUN mvn dependency:go-offline
COPY src ./src
RUN mvn clean package -DskipTests


#runtime kısmı

FROM eclipse-temurin:17-jre
WORKDIR /app
#builder katmanında olusan jar -> buradaki dizine app.jar olarak al
COPY --from=builder /app/target/*.jar app.jar
CMD ["java", "-jar", "app.jar"]

