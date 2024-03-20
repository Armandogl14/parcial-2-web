package org.example;

import io.javalin.Javalin;
import io.javalin.http.staticfiles.Location;
import org.example.clases.Usuario;
import org.example.controllers.EncuestaController;
import org.example.controllers.HomeController;
import org.example.controllers.UserController;
import org.example.servicios.BootStrapServices;
import org.example.servicios.UserServices;

public class Main {
    public static void main(String[] args) {
        BootStrapServices.getInstancia().init();
        if (UserServices.getInstancia().find("admin") == null) {
            UserServices.getInstancia().insert(new Usuario("admin", "admin", true));
        }
        Javalin app = Javalin.create(config -> {
            config.staticFiles.add(staticFileConfig -> {
                staticFileConfig.hostedPath = "/";
                staticFileConfig.directory = "public";
                staticFileConfig.location = Location.CLASSPATH;
                staticFileConfig.precompress = false;
                staticFileConfig.aliasCheck = null;
            });
        }).start(7000);

        new HomeController(app).aplicarRutas();
        new UserController(app).aplicarRutas();
        new EncuestaController(app).aplicarRutas();
    }
}