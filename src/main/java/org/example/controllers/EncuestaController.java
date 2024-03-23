package org.example.controllers;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import io.javalin.Javalin;
import io.javalin.rendering.JavalinRenderer;
import io.javalin.rendering.template.JavalinThymeleaf;
import org.example.clases.Registro;
import org.example.clases.Respuesta;
import org.example.clases.Usuario;
import org.example.servicios.RegistroServices;
import org.example.servicios.RespuestaServices;
import org.example.servicios.UserServices;

public class EncuestaController extends BaseController{
    public EncuestaController(Javalin app) {
        super(app);
        registerTemplates();
    }

    @Override
    public void aplicarRutas() {
        app.get("/encuesta", ctx -> {
            ctx.render("public/templates/encuesta.html");
        });

        app.post("/encuesta", ctx -> {
            Usuario usuario = ctx.sessionAttribute("usuario");
            String nombre = ctx.formParam("nombre");
            String sector = ctx.formParam("Sector");
            String nivelEdicativo = ctx.formParam("nivelEscolar");
            double latitud = Double.parseDouble(ctx.formParam("latitud"));
            double longitud = Double.parseDouble(ctx.formParam("longitud"));
            Respuesta respuesta = new Respuesta(nombre, sector, nivelEdicativo, usuario, latitud, longitud);
            RespuestaServices.getInstancia().insert(respuesta);
            RegistroServices.getInstancia().insert(new Registro(respuesta, usuario));
            ctx.redirect("/");
        });

        app.get("encuesta/stored", ctx -> {
            ctx.render("public/templates/listar-stored-register.html");
        });

        app.ws("/encuesta/sincronizar", ws -> {
            ws.onConnect(session -> {
                System.out.println("Conectado");
            });
            ws.onMessage(ctx -> {
                String message = ctx.message();
                System.out.println("Received: " + message);

                // Parsear el mensaje JSON a un JsonObject
                JsonElement jelement = JsonParser.parseString(message);
                if (jelement.isJsonArray()){
                    JsonArray jsonArray = jelement.getAsJsonArray();

                    for(JsonElement element : jsonArray){
                        JsonObject jobject = element.getAsJsonObject();
                        // Obtener cada campo individualmente
                        String nombre = jobject.get("nombre").getAsString();
                        String sector = jobject.get("sector").getAsString();
                        String nivelEscolar = jobject.get("nivelEscolar").getAsString();
                        String usuario = jobject.get("usuario").getAsString();
                        double latitud = jobject.get("latitud").getAsDouble();
                        double longitud = jobject.get("longitud").getAsDouble();

                        Usuario user = UserServices.getInstancia().find(usuario);
                        Respuesta respuesta = new Respuesta(nombre, sector, nivelEscolar, user, latitud, longitud);
                        Registro registro = new Registro(respuesta, user);

                        try {
                            RespuestaServices.getInstancia().insert(respuesta);
                            RegistroServices.getInstancia().insert(registro);
                            System.out.println("Registro almacenado en la base de datos");
                        } catch (Exception e) {
                            System.out.println("Error al procesar el registro: " + e.getMessage());
                        }
                    }
                }
            });

            ws.onClose(ctx -> {
                System.out.println("Desconectado");
            });

            ws.onBinaryMessage(ctx -> {
                System.out.println("Jorgen von Strangle");
            });

            ws.onError(ctx -> {
                System.out.println("Error en WS");
            });
        });
    }
}
