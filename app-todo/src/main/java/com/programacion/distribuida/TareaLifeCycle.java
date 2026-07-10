package com.programacion.distribuida;

import io.quarkus.runtime.ShutdownEvent;
import io.quarkus.runtime.StartupEvent;
import io.vertx.core.Vertx;
import io.vertx.ext.consul.CheckOptions;
import io.vertx.ext.consul.ConsulClient;
import io.vertx.ext.consul.ConsulClientOptions;
import io.vertx.ext.consul.ServiceOptions;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import java.net.InetAddress;
import java.util.List;

@ApplicationScoped
public class TareaLifeCycle {

    @Inject
    @ConfigProperty(name = "quarkus.http.port", defaultValue = "8080")
    int appPort;

    @Inject
    @ConfigProperty(name = "consul.host", defaultValue = "localhost")
    String consulHost;

    @Inject
    @ConfigProperty(name = "consul.port", defaultValue = "8500")
    int consutlPort;

    String serviceId;

    public void init (@Observes StartupEvent event, Vertx vertx){

        System.out.println("ciclo de vida del sistema inciando");
        try {

            // ocpiones de consul para registrar el puerto y el host
            ConsulClientOptions options = new ConsulClientOptions()
                    .setHost(consulHost)
                    .setPort(consutlPort);

            // configuracmos el cliente
            ConsulClient client = ConsulClient.create(vertx, options);

            // Armamos la ip
            String ipAddrees = InetAddress.getLocalHost().getHostAddress();
            serviceId = "app-todo-" + ipAddrees + ":" + appPort;

            var urlCheck = "http://"+ipAddrees+":"+appPort+"/q/health";

            // poopciones para ver si la app esta arriba
            CheckOptions checkOptions = new CheckOptions()
                    .setHttp(urlCheck)
                    .setInterval("10s")
                    .setDeregisterAfter("10s");

            // tagas para traefik
            var tags = List.of(
                    "traefik.enable=true",
                    "traefik.http.routers.router-app-tareas.rule=PathPrefix(`/tareas`)",
                    "traefik.http.routers.router-app-tareas.middlewares=middlewares-tareas",
                    "traefik.http.middlewares.middlewares-tareas.stripprefix.prefixes=/tareas"
            );

            // Opciones para registrar el servicio
            ServiceOptions serviceOptions = new ServiceOptions()
                    .setName("app-todos")
                    .setId(serviceId)
                    .setAddress(ipAddrees)
                    .setPort(appPort)
                    .setCheckOptions(checkOptions)
                    .setTags(tags);

            // registración del cliente
            client.registerService(serviceOptions)
                    .onSuccess(it -> System.out.println("App-Todos servicio registrado en consul ID: " + serviceId))
                    .onFailure(it -> System.out.println("Fallo registrar la ap en consult: " + it.getMessage()));

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public void destroy (@Observes ShutdownEvent event, Vertx vertx){
        System.out.println("app-todo destrueyendo el ciclo de vida");
        ConsulClientOptions options = new ConsulClientOptions()
                .setHost(consulHost)
                .setPort(consutlPort);

        ConsulClient client = ConsulClient.create(vertx, options);

        client.deregisterService(serviceId)
                .onSuccess(it -> System.out.println("App-Todos servicio desregistrado de consul con ID: " + serviceId))
                .onFailure(it -> System.out.println("Fallo desregistrar la ap en consult: " + it.getMessage()));



    }
}
