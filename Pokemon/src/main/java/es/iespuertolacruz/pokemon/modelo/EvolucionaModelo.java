package es.iespuertolacruz.pokemon.modelo;

import es.iespuertolacruz.pokemon.api.Evoluciona;
import es.iespuertolacruz.pokemon.excepciones.PersistenciaException;

public class EvolucionaModelo {
    // Variables de clase

    DdBbSqLite persistencia;
    private static final String TABLA = "TIPO";
    private static final String CLAVE = "nombre";
    // Constructores

    public EvolucionaModelo() throws PersistenciaException {
        persistencia = new DdBbSqLite(TABLA, CLAVE, null, null);
    }

    public void insertar(Evoluciona evoluciona) throws PersistenciaException {
        String sql = "INSERT INTO " + TABLA + " VALUES ('" + evoluciona.getNumeroPokedexOrigen() + "','"
                + evoluciona.getNumeroPokedexDestino()+ "','"+evoluciona.getModoEvoluciona() + "');";
        persistencia.update(sql);
    }

    public void eliminar(Evoluciona evolucionan) throws PersistenciaException {
        String sql = "DELETE FROM " + TABLA + " WHERE " + CLAVE + " = '" + evolucionan.getNumeroPokedexOrigen() + "';";
        persistencia.update(sql);
    }
/**
    public Evoluciona buscar(int numeroPokedexOrigen) throws PersistenciaException {
        return (Evoluciona) persistencia.buscarElemento(NumeroPokedexOrigen);
    }
*/
    public void modificar(Evoluciona evoluciona) throws PersistenciaException {
        String sql = "UPDATE " + TABLA + " SET Tipo_Principal = '" + evoluciona.getNumeroPokedexDestino()+ evoluciona
                .getModoEvoluciona()+ "' WHERE " + CLAVE
                + " = '" + evoluciona.getNumeroPokedexOrigen() + "';";
        persistencia.update(sql);
    }
}
