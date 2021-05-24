package es.iespuertolacruz.pokemon.modelo;

import es.iespuertolacruz.pokemon.api.Estado;
import es.iespuertolacruz.pokemon.excepciones.PersistenciaException;

public class EstadoModelo {
    // Variables de clase

    DdBbSqLite persistencia;
    private static final String TABLA = "TIPO";
    private static final String CLAVE = "nombre";
    // Constructores

    public EstadoModelo() throws PersistenciaException {
        persistencia = new DdBbSqLite(TABLA, CLAVE, null, null);
    }

    public void insertar(Estado estado) throws PersistenciaException {
        String sql = "INSERT INTO " + TABLA + " VALUES ('" + estado.getId() + "','" + estado.getNombre() + "','"
                + estado.getPersistencia() + "','" + estado.getEfecto() + "');";
        persistencia.update(sql);
    }

    public void eliminar(Estado estado) throws PersistenciaException {
        String sql = "DELETE FROM " + TABLA + " WHERE " + CLAVE + " = '" + estado.getId() + "';";
        persistencia.update(sql);
    }

    public Estado buscar(int id) throws PersistenciaException {
        return (Estado) persistencia.buscarElemento(id);
    }

    public void modificar(Estado estado) throws PersistenciaException {
        String sql = "UPDATE " + TABLA + " SET Tipo_Principal = '" + estado.getNombre() + "','"
                + estado.getPersistencia() + "','" + estado.getEfecto() + "' WHERE " + CLAVE + " = '" + estado.getId()
                + "';";
        persistencia.update(sql);
    }
}
