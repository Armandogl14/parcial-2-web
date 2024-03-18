package org.example.clases;

import java.util.Date;
import java.util.List;

public class RespuestaEncuesta {
    private int id;
    private Usuario usuario;
    private Encuesta encuesta;
    private List<Respuesta> respuestas;
    private String coords;
    private Date fecha;

    public RespuestaEncuesta(int id, Usuario usuario, Encuesta encuesta, List<Respuesta> respuestas, String coords) {
        this.id = id;
        this.usuario = usuario;
        this.encuesta = encuesta;
        this.respuestas = respuestas;
        this.coords = coords;
        this.fecha = new Date();
    }
}
