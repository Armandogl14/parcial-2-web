package org.example.clases;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;
@Entity
public class Usuario {
    @Id
    private String username;
    private String password;
    private String nombre;
    private boolean administrator;

    public Usuario(String username, String password, String nombre, boolean administrator) {
        this.username = username;
        this.password = password;
        this.nombre = nombre;
        this.administrator = administrator;
    }

    public Usuario() {

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

    public boolean isAdministrator() {
        return administrator;
    }
}
