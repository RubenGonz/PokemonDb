package es.iespuertolacruz.pokemon.modelo;

import es.iespuertolacruz.pokemon.api.Conoce;
import es.iespuertolacruz.pokemon.excepciones.PersistenciaException;

public class ConoceModelo {
    // Variables de clase

    DdBbSqLite persistencia;
    private static final String TABLA = "TIPO";
    private static final String CLAVE = "nombre";
    // Constructores

    public ConoceModelo() throws PersistenciaException {
        persistencia = new DdBbSqLite(TABLA, CLAVE, null, null);
    }

    public void insertar(Conoce conoce) throws PersistenciaException {
        String sql = "INSERT INTO " + TABLA + " VALUES ('" + conoce.getNumeroPokedex() + "','"
                + conoce.getIdMovimiento() + "');";
        persistencia.update(sql);
    }

    public void eliminar(Conoce conoce) throws PersistenciaException {
        String sql = "DELETE FROM " + TABLA + " WHERE " + CLAVE + " = '" + conoce.getNumeroPokedex() + "';";
        persistencia.update(sql);
    }

    public Conoce buscar(int numeroPokedex) throws PersistenciaException {
        return (Conoce) persistencia.buscarElemento(numeroPokedex);
    }

    public void modificar(Conoce conoce) throws PersistenciaException {
         String sql = "UPDATE " + TABLA + " SET Tipo_Principal = '" + conoce.getIdMovimiento() + "' WHERE " + CLAVE + " = '"
                + conoce.getNumeroPokedex() + "';";
        persistencia.update(sql);
    }

}
