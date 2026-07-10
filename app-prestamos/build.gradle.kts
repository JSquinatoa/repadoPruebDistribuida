plugins {
    id("java")
    id("io.quarkus") version "3.37.2"
    id("io.freefair.lombok") version "9.2.0"
}

repositories {
    mavenCentral()
    mavenLocal()
}



val quarkusPlatformVersion = "3.37.2"

dependencies {
    // Sincroniza versiones: Garantiza que todas las librerías de abajo sean compatibles entre sí.
    implementation(enforcedPlatform("io.quarkus.platform:quarkus-bom:${quarkusPlatformVersion}"))

    // Recibe peticiones HTTP: Habilita el uso de @Path, @GET, @POST (Servidor).
    implementation("io.quarkus:quarkus-rest")
    // Convierte datos: Transforma objetos Java a JSON automáticamente para las respuestas.
    implementation("io.quarkus:quarkus-rest-jsonb")

    // Inyección de dependencias: Habilita el uso de @Inject y @ApplicationScoped (CDI).
    implementation("io.quarkus:quarkus-arc")

    // Base de Datos sin SQL: Mapea tablas (@Entity) y da métodos rápidos (.listAll(), .persist()).
    implementation("io.quarkus:quarkus-hibernate-orm-panache")
    // Conector de Base de Datos: El driver que conecta tu código con PostgreSQL.
    implementation("io.quarkus:quarkus-jdbc-postgresql")


    // Para el Consul: Le dice a Consul si tu app está "viva" o se colgó (/q/health)
    implementation("io.quarkus:quarkus-smallrye-health")

    // Para el Monitoreo: Crea la ruta /q/metrics para que Prometheus lea los datos (/q/metrics)
    implementation("io.quarkus:quarkus-micrometer-registry-prometheus")

    // Registrar ciclo de vida de consult
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
