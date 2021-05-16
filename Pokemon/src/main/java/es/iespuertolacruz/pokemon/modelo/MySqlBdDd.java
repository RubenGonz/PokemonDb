package es.iespuertolacruz.pokemon.modelo;

import es.iespuertolacruz.pokemon.excepciones.PersistenciaException;

public class MySqlBdDd extends Bbdd {
    
    public MySqlBdDd(String driver, String url, String usuario, String password) throws PersistenciaException {
        super(driver, url, usuario, password);
    }

}
