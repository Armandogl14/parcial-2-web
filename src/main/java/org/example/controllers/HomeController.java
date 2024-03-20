package org.example.controllers;

import io.javalin.Javalin;
import io.javalin.rendering.JavalinRenderer;
import io.javalin.rendering.template.JavalinThymeleaf;

public class HomeController extends BaseController{
    public HomeController(Javalin app) {
        super(app);
        registerTemplates();
    }

    public void registerTemplates(){
        JavalinRenderer.register(new JavalinThymeleaf(), ".html");
    }

    @Override
    public void aplicarRutas() {
        app.get("/", ctx -> {
            ctx.render("templates/index.html");
        });
    }
}