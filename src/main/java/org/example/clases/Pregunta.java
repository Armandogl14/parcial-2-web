package org.example.clases;

import java.util.ArrayList;
import java.util.List;

public class Pregunta {
    private int id;
    private String pregunta;
    private String tipo;
    private List<String> opciones;

    public Pregunta(int id, String pregunta, String tipo, List<String> opciones) {
        this.id = id;
        this.pregunta = pregunta;
        this.tipo = tipo;
        this.opciones = opciones;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPregunta() {
        return pregunta;
    }

    public void setPregunta(String pregunta) {
        this.pregunta = pregunta;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public List<String> getOpciones() {
        return opciones;
    }

    public void setOpciones(List<String> opciones) {
        this.opciones = opciones;
    }
}
