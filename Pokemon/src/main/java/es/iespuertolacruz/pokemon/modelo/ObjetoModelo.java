package es.iespuertolacruz.pokemon.modelo;

import es.iespuertolacruz.pokemon.api.Objeto;
import es.iespuertolacruz.pokemon.excepciones.PersistenciaException;

public class ObjetoModelo {
    // Variables de clase

    DdBbSqLite persistencia;
    private static final String TABLA = "TIPO";
    private static final String CLAVE = "nombre";

    // Constructores

    public ObjetoModelo() throws PersistenciaException {
        persistencia = new DdBbSqLite(TABLA, CLAVE, null, null);
    }

    public void insertar(Objeto objeto) throws PersistenciaException {
        String sql = "INSERT INTO " + TABLA + " VALUES ('" + objeto.getId() + "','"
                + objeto.getNombre()+ "','"+objeto.getModoObtencion() + "');";
        persistencia.update(sql);
    }

    public void eliminar(Objeto objeto) throws PersistenciaException {
        String sql = "DELETE FROM " + TABLA + " WHERE " + CLAVE + " = '" + objeto.getId() + "';";
        persistencia.update(sql);
    }

    public Objeto buscar(int id) throws PersistenciaException {
        return (Objeto) persistencia.buscarElemento(id);
    }

    public void modificar(Objeto objeto) throws PersistenciaException {
        String sql = "UPDATE " + TABLA + " SET Tipo_Principal = '" + objeto.getNombre() + "','"
                + objeto.getModoObtencion() + "' WHERE " + CLAVE
                + " = '" + objeto.getId() + "';";
        persistencia.update(sql);
    }

}
