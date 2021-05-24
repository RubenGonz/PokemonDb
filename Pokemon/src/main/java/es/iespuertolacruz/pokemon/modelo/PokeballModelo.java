package es.iespuertolacruz.pokemon.modelo;

import es.iespuertolacruz.pokemon.api.Pokeball;
import es.iespuertolacruz.pokemon.excepciones.PersistenciaException;

public class PokeballModelo {
    // Variables de clase

    DdBbSqLite persistencia;
    private static final String TABLA = "TIPO";
    private static final String CLAVE = "nombre";

    // Constructores

    public PokeballModelo() throws PersistenciaException {
        persistencia = new DdBbSqLite(TABLA, CLAVE, null, null);
    }

    public void insertar(Pokeball pokeball) throws PersistenciaException {
        String sql = "INSERT INTO " + TABLA + " VALUES ('" + pokeball.getIdObjeto() + "','"
                + pokeball.getRatio() + "');";
        persistencia.update(sql);
    }

    public void eliminar(Pokeball pokeball) throws PersistenciaException {
        String sql = "DELETE FROM " + TABLA + " WHERE " + CLAVE + " = '" + pokeball.getIdObjeto() + "';";
        persistencia.update(sql);
    }
/**
    public Pokeball buscar(int idObjeto) throws PersistenciaException {
        return (Pokeball) persistencia.buscarElemento(idObjeto);
    }
*/
    public void modificar(Pokeball pokeball) throws PersistenciaException {
        String sql = "UPDATE " + TABLA + " SET Ratio = '" + pokeball.getRatio() + "' WHERE " + CLAVE
                + " = '" + pokeball.getIdObjeto() + "';";
        persistencia.update(sql);
    }

}
