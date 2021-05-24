package es.iespuertolacruz.pokemon.modelo;

import es.iespuertolacruz.pokemon.api.EstadisticasBase;
import es.iespuertolacruz.pokemon.excepciones.PersistenciaException;

public class EstadisticasBaseModelo {
    // Variables de clase

    DdBbSqLite persistencia;
    private static final String TABLA = "TIPO";
    private static final String CLAVE = "nombre";
    // Constructores

    public EstadisticasBaseModelo() throws PersistenciaException {
        persistencia = new DdBbSqLite(TABLA, CLAVE, null, null);
    }

    public void insertar(EstadisticasBase estadisticasBase) throws PersistenciaException {
        String sql = "INSERT INTO " + TABLA + " VALUES ('" + estadisticasBase.getId() + "','"
                + estadisticasBase.getPsBase() + "','" + estadisticasBase.getAtaqueBase() + "','"
                + estadisticasBase.getDefensaBase() + "','" + estadisticasBase.getAtaqueEspecialBase() + "','"
                + estadisticasBase.getDefensaEspecialBase()+ "','"+ estadisticasBase.getVelocidadBase() + "');";
        persistencia.update(sql);
    }

    public void eliminar(EstadisticasBase estadisticasBase) throws PersistenciaException {
        String sql = "DELETE FROM " + TABLA + " WHERE " + CLAVE + " = '" + estadisticasBase.getId() + "';";
        persistencia.update(sql);
    }

    public EstadisticasBase buscar(int id) throws PersistenciaException {
        return (EstadisticasBase) persistencia.buscarElemento(id);
    }

    public void modificar(EstadisticasBase estadisticasBase) throws PersistenciaException {
        String sql = "UPDATE " + TABLA + " SET Tipo_Principal = '"  +estadisticasBase.getPsBase() + "','"
                + estadisticasBase.getAtaqueBase() + "','" + estadisticasBase.getDefensaBase() + "','"
                + estadisticasBase.getAtaqueEspecialBase() + "','" + estadisticasBase.getDefensaEspecialBase() + "','"
                + estadisticasBase.getVelocidadBase() + "' WHERE " + CLAVE
                + " = '" + estadisticasBase.getId() + "';";
        persistencia.update(sql);
    }
}
