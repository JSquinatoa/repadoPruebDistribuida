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
    // ==========================================
    // 1. DEPENDENCIAS BASE (Las que conservas)
    // ==========================================

    // Alinea las versiones para que ninguna librería pelee con otra
    implementation(enforcedPlatform("io.quarkus.platform:quarkus-bom:${quarkusPlatformVersion}"))
    // Para poder crear tus endpoints (@Path, @GET, @POST)
    implementation("io.quarkus:quarkus-rest")
    // Para que Quarkus convierta tus objetos Java a texto JSON solito
    implementation("io.quarkus:quarkus-rest-jsonb")

    // Para consumir APIs de otros (Ej: JSONPlaceholder)
    implementation("io.quarkus:quarkus-rest-client")
    // Para que la respuesta de esa API externa se convierta a un objeto Java
    implementation("io.quarkus:quarkus-rest-client-jsonb")

    // Habilita el uso de @Inject (Inyección de dependencias)
    implementation("io.quarkus:quarkus-arc")
    // Para usar PanacheEntity y guardar en BD sin escribir SQL (.persist(), .listAll())
    implementation("io.quarkus:quarkus-hibernate-orm-panache")
    // El driver para poder conectarse a tu Postgres
    implementation("io.quarkus:quarkus-jdbc-postgresql")
    // Para correr scripts SQL automáticamente al iniciar (Control de versiones BD)
    implementation("io.quarkus:quarkus-flyway")
    runtimeOnly("org.flywaydb:flyway-database-postgresql:12.5.0")
    // ==========================================
    // 2. NUEVAS DEPENDENCIAS (Las que se aumentan para el examen)
    // ==========================================
    // Para el Consul: Le dice a Consul si tu app está "viva" o se colgó (/q/health)
    implementation("io.quarkus:quarkus-smallrye-health")
    // Para el Monitoreo: Crea la ruta /q/metrics para que Prometheus lea los datos
    implementation("io.quarkus:quarkus-micrometer-registry-prometheus")

    // para registrar ciclo de vida de consult
    implementation("io.smallrye.reactive:smallrye-mutiny-vertx-consul-client")

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
