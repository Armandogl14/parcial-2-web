package org.example.controllers;

import io.javalin.Javalin;
import io.javalin.rendering.JavalinRenderer;
import io.javalin.rendering.template.JavalinThymeleaf;
import org.example.clases.Registro;
import org.example.clases.Respuesta;
import org.example.clases.Usuario;
import org.example.servicios.RegistroServices;
import org.example.servicios.RespuestaServices;

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
    }

}
