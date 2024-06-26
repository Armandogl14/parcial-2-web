package org.example.controllers;

import io.javalin.Javalin;
import io.javalin.rendering.JavalinRenderer;
import io.javalin.rendering.template.JavalinThymeleaf;
import org.example.clases.Usuario;
import org.example.servicios.UserServices;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserController extends BaseController{
    public UserController(Javalin app) {
        super(app);
        registerTemplates();
    }

    @Override
    public void aplicarRutas() {
        app.get("/user/register", ctx -> {
            Map<String, Object> model = new HashMap<>();

            model.put("titulo", "Registrar");
            ctx.render("/public/templates/register.html", model);
        });

        app.post("/user/register", ctx -> {
            String user = ctx.formParam("usuario");
            String name = ctx.formParam("nombre");
            String pass = ctx.formParam("password");

            Usuario existingUser = UserServices.getInstancia().findUserByUsername(user);
            if (existingUser != null) {
                ctx.render("/templates/register.html", Map.of("error", "El nombre de usuario ya existe"));
            }
            else{
                Usuario temp = new Usuario(user, pass, false);
                UserServices.getInstancia().insert(temp);
                ctx.sessionAttribute("username", temp);
                ctx.redirect("/");
            }
        });
        app.get("/user/crear", ctx -> {
            Map<String, Object> model = new HashMap<>();

            model.put("titulo", "Crear");
            ctx.render("/public/templates/crear-user.html", model);
        });

        app.post("/user/crear", ctx -> {
            String usuario = ctx.formParam("usuario");
            String password = ctx.formParam("password");
            boolean admin = ctx.formParam("Admin") != null;

            Usuario existingUser = UserServices.getInstancia().findUserByUsername(usuario);
            if (existingUser != null) {
                ctx.render("/templates/register.html", Map.of("error", "El nombre de usuario ya existe"));
            }
            else{
                Usuario temp = new Usuario(usuario, password, admin);
                UserServices.getInstancia().insert(temp);
                ctx.redirect("/user/list");
            }
        });

        app.get("/user/login", ctx -> {
            Map<String, Object> model = new HashMap<>();

            model.put("titulo", "Log in");
            ctx.render("/public/templates/Login.html", model);
        });

        app.post("/user/login", ctx -> {
            String user = ctx.formParam("usuario");
            String pass = ctx.formParam("password");

            Usuario aux = UserServices.getInstancia().find(user);
            if(aux != null){
                if(aux.getPassword().equalsIgnoreCase(pass)){
                    if (ctx.formParam("rememberMe") != null) {
                        ctx.cookie("rememberedUser", aux.getUsername(),600);
                    }
                    ctx.sessionAttribute("username", aux);
                    ctx.redirect("/");
                }

                else{
                    ctx.render("/public/templates/Login.html", Map.of("error", "Usuario o contraseña incorrectos"));
                }
            }
            else{
                ctx.render("/public/templates/Login.html", Map.of("error", "Usuario no existe"));
            }
        });
        app.before("/user/list", ctx -> {
            Usuario usuario = ctx.sessionAttribute("username");
            if (usuario == null || !usuario.isAdministrator()) {
                ctx.redirect("/");
            }
        });

        app.get("/user/list", ctx -> {
            String pageParam = ctx.queryParam("page");
            int page = (pageParam != null) ? Integer.parseInt(pageParam) : 1;
            List<Usuario> usuarios = UserServices.getInstancia().findAll(page, 5);
            int totalUsers = UserServices.getInstancia().findAll().size();
            int totalPages = (int) Math.ceil((double) totalUsers / 5);
            ctx.render("/public/templates/user-list.html", Map.of("usuarios", usuarios, "totalPages", totalPages, "currentPage", page));
        });

        app.get("/user/close", ctx -> {
            ctx.removeCookie("rememberedUser");
            ctx.req().getSession().invalidate();
            ctx.redirect("/");
        });

        app.get("/user/editar/{username}", ctx -> {
            Usuario user = UserServices.getInstancia().find(ctx.pathParam("username"));
            if (user == null) {
                ctx.redirect("/");
            }
            Map<String, Object> model = new HashMap<>();
            model.put("usuario", user);
            ctx.render("/public/templates/editar-usuario.html", model);
        });

        app.post("user/editar/{username}", ctx -> {
            Usuario user = UserServices.getInstancia().find(ctx.pathParam("username"));
            if (user == null) {
                ctx.redirect("/");
            }
            String usuario = ctx.formParam("usuario");
            String password = ctx.formParam("password");
            boolean admin = ctx.formParam("Admin") != null;

            user.setUsername(usuario);
            user.setPassword(password);
            /*if(admin){
                user.setAdministrator(true);
            }
            else{
                user.setAdministrator(false);
            }*/
            user.setAdministrator(admin);

            UserServices.getInstancia().update(user);
            ctx.redirect("/user/list");
        });

        app.post("/user/borrar/{username}", ctx -> {
            Usuario user = UserServices.getInstancia().find(ctx.pathParam("username"));
            if (user == null) {
                ctx.redirect("/");
            }
            UserServices.getInstancia().delete(user);
            ctx.redirect("/user/list");
        });
    }
}