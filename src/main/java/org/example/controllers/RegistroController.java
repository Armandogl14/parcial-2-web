package org.example.controllers;

import io.javalin.Javalin;
import org.example.clases.Registro;
import org.example.servicios.RegistroServices;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RegistroController extends BaseController{
    public RegistroController(Javalin app) {
        super(app);
        registerTemplates();
    }

    @Override
    public void aplicarRutas() {
        app.get("/registro", ctx -> {
            List<Registro> registros = RegistroServices.getInstancia().findAll();
            Map<String, Object> model = new HashMap<>();
            model.put("registros", registros);
            ctx.render("public/templates/listar-registro.html",model);
        });



    }
}
