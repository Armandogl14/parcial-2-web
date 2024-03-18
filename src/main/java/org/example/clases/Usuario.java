package org.example.clases;

import java.util.ArrayList;
import java.util.List;

public class Usuario {
    private String username;
    private String password;
    private String nombre;
    private List<Rol> roles;

    public Usuario(String username, String password, String nombre, List<Rol> roles) {
        this.username = username;
        this.password = password;
        this.nombre = nombre;
        this.roles = roles;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Rol> getRoles() {
        return roles;
    }

    public void setRoles(List<Rol> roles) {
        this.roles = roles;
    }
}
