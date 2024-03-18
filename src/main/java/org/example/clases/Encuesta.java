package org.example.clases;

import java.util.List;

public class Encuesta {
    private int id;
    private String titulo;
    private String descripcion;
    private Usuario autor;
    private List<Pregunta> preguntas;

    public Encuesta(int id, String titulo, String descripcion, Usuario autor, List<Pregunta> preguntas) {
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.autor = autor;
        this.preguntas = preguntas;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Usuario getAutor() {
        return autor;
    }

    public void setAutor(Usuario autor) {
        this.autor = autor;
    }

    public List<Pregunta> getPreguntas() {
        return preguntas;
    }

    public void setPreguntas(List<Pregunta> preguntas) {
        this.preguntas = preguntas;
    }
}
