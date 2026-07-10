plugins {
    id("java")
    id("io.quarkus") version "3.37.2"
    id("io.freefair.lombok") version "9.2.0"
}

repositories {
    mavenCentral()
}

val quarkusPlatformVersion = "3.37.2"

dependencies {
    implementation(enforcedPlatform("io.quarkus.platform:quarkus-bom:${quarkusPlatformVersion}"))

    // Peticiones HTTP
    implementation("io.quarkus:quarkus-rest")
    // Producir y consumir json
    implementation("io.quarkus:quarkus-rest-jsonb")

    // Es la dependencia de CDI
    implementation("io.quarkus:quarkus-arc")

    // Persistencia y listados de bases de datos
    implementation("io.quarkus:quarkus-hibernate-orm-panache")
    // Configuraciones para conexion en .properties
    implementation("io.quarkus:quarkus-jdbc-postgresql")

    // Llamadas externas: Permite que esta app consuma las APIs de OTROS microservicios (Cliente).
    implementation("io.quarkus:quarkus-rest-client")
    // Convierte datos externos: Transforma a JSON lo que envías/recibes con el rest-client.
    implementation("io.quarkus:quarkus-rest-client-jsonb")

    // Microprofile Health
    implementation("io.quarkus:quarkus-smallrye-health")

}

group = "org.acme"
version = "1.0.0-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_25
    targetCompatibility = JavaVersion.VERSION_25
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
    options.compilerArgs.add("-parameters")
}
