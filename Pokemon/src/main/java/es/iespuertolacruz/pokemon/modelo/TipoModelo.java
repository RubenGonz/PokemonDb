package es.iespuertolacruz.pokemon.modelo;

import es.iespuertolacruz.pokemon.api.Tipo;
import es.iespuertolacruz.pokemon.excepciones.PersistenciaException;

public class TipoModelo {

    //Variables de clase

    DdBbSqLite persistencia;
    private static final String TABLA = "TIPO";
    private static final String CLAVE = "nombre";

    // Constructores

    public TipoModelo() throws PersistenciaException {
        persistencia = new DdBbSqLite(null, null);
    }

    //Metodos y funciones
    
    public void insertar(Tipo tipo) throws PersistenciaException {
        String sql = "INSERT INTO " + TABLA + " VALUES ('" + 
        tipo.getNombre() + "','" + 
        tipo.getColor()+ "');";
        persistencia.update(sql);
    }

    public void eliminar(Tipo tipo) throws PersistenciaException {
        String sql = "DELETE FROM " + TABLA + 
        " WHERE " + CLAVE + " = '" + tipo.getNombre() + "';";
        persistencia.update(sql);
    }

    public Tipo buscar(String nombre) throws PersistenciaException {
        return (Tipo) persistencia.buscarTipo(nombre);
    }

    public void modificar(Tipo tipo) throws PersistenciaException {
        String sql = "UPDATE " + TABLA + 
        " SET color = '" + tipo.getColor() +
        "' WHERE " + CLAVE + " = '" + tipo.getNombre()+"';";
        persistencia.update(sql);
    }

}
