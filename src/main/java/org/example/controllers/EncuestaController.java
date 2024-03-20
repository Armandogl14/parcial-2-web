package org.example.controllers;

import io.javalin.Javalin;
import io.javalin.rendering.JavalinRenderer;
import io.javalin.rendering.template.JavalinThymeleaf;

public class EncuestaController extends BaseController{
    public EncuestaController(Javalin app) {
        super(app);
        registerTemplates();
    }

    public void registerTemplates(){
        JavalinRenderer.register(new JavalinThymeleaf(), ".html");
    }

    @Override
    public void aplicarRutas() {
        app.get("/encuesta", ctx -> {
            ctx.render("templates/encuesta.html");
        });
    }
}
