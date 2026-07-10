package com.programacion.distribuida.clientes.rest;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/")
public class FrontendRest {

    @GET
    @Produces(MediaType.TEXT_HTML)
    public String index() {
        return "<!DOCTYPE html>\n" +
                "<html lang=\"es\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "    <title>Administracion de Clientes y Facturas</title>\n" +
                "    <script src=\"https://unpkg.com/vue@3/dist/vue.global.js\"></script>\n" +
                "    <style>\n" +
                "        body { font-family: Arial, sans-serif; margin: 40px; background-color: #f4f4f9;}\n" +
                "        h1, h2 { color: #333; }\n" +
                "        table { width: 100%; border-collapse: collapse; margin-bottom: 30px; background: white;}\n" +
                "        th, td { border: 1px solid #ddd; padding: 12px; text-align: left; }\n" +
                "        th { background-color: #007bff; color: white; }\n" +
                "    </style>\n" +
                "</head>\n" +
                "<body>\n" +
                "    <div id=\"app\">\n" +
                "        <h1>Panel de Administracion (Vue 3)</h1>\n" +
                "        <h2>Lista de Clientes</h2>\n" +
                "        <table>\n" +
                "            <thead><tr><th>ID</th><th>Nombre</th><th>Apellido</th><th>Direccion</th></tr></thead>\n" +
                "            <tbody>\n" +
                "                <tr v-for=\"cliente in clientes\" :key=\"cliente.id\">\n" +
                "                    <td>{{ cliente.id }}</td><td>{{ cliente.nombre }}</td><td>{{ cliente.apellido }}</td><td>{{ cliente.direccion }}</td>\n" +
                "                </tr>\n" +
                "            </tbody>\n" +
                "        </table>\n" +
                "        <h2>Lista de Facturas</h2>\n" +
                "        <table>\n" +
                "            <thead><tr><th>ID Factura</th><th>ID Cliente</th><th>ID Producto</th><th>Precio Venta</th></tr></thead>\n" +
                "            <tbody>\n" +
                "                <tr v-for=\"factura in facturas\" :key=\"factura.id\">\n" +
                "                    <td>{{ factura.id }}</td><td>{{ factura.cliente_id }}</td><td>{{ factura.producto_id }}</td><td>${{ factura.precio }}</td>\n" +
                "                </tr>\n" +
                "            </tbody>\n" +
                "        </table>\n" +
                "    </div>\n" +
                "    <script>\n" +
                "        const { createApp, ref, onMounted } = Vue;\n" +
                "        createApp({\n" +
                "            setup() {\n" +
                "                const clientes = ref([]);\n" +
                "                const facturas = ref([]);\n" +
                "                const cargarDatos = async () => {\n" +
                "                    try {\n" +
                "                        const resClientes = await fetch('/clientes');\n" +
                "                        clientes.value = await resClientes.json();\n" +
                "                        const resFacturas = await fetch('/facturas');\n" +
                "                        facturas.value = await resFacturas.json();\n" +
                "                    } catch (error) { console.error('Error:', error); }\n" +
                "                };\n" +
                "                onMounted(() => { cargarDatos(); });\n" +
                "                return { clientes, facturas }\n" +
                "            }\n" +
                "        }).mount('#app');\n" +
                "    </script>\n" +
                "</body>\n" +
                "</html>";
    }
}
