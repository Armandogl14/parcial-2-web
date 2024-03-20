package org.example.servicios;

import org.example.clases.Usuario;

public class UserServices extends DataBaseServices<Usuario>{
    private static UserServices instancia;
    public UserServices() {
        super(Usuario.class);
    }

    public static UserServices getInstancia(){
        if(instancia==null){
            instancia = new UserServices();
        }
        return instancia;
    }
}
