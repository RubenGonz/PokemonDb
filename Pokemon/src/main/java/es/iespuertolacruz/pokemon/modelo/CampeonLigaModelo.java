package es.iespuertolacruz.pokemon.modelo;


import es.iespuertolacruz.pokemon.api.CampeonLiga;
import es.iespuertolacruz.pokemon.excepciones.PersistenciaException;

/**
 * Clase modelo que tranaja con la clase campeonliga
 */
public class CampeonLigaModelo {
   
      // Variables de clase

    DdBbSqLite persistencia;
    private static final String TABLA = "TIPO";
    private static final String CLAVE = "nombre";

        // Constructores

        public CampeonLigaModelo() throws PersistenciaException {
            persistencia = new DdBbSqLite(TABLA, CLAVE, null, null);
        }

        public void insertar(CampeonLiga  campeonLiga) throws PersistenciaException {
            String sql = "INSERT INTO " + TABLA + " VALUES ('" + campeonLiga.getRegion() + "','"
                    + campeonLiga.getIdEntrenador() + "');";
            persistencia.update(sql);
        }

        public void eliminar(CampeonLiga campeonLiga) throws PersistenciaException {
            String sql = "DELETE FROM " + TABLA + " WHERE " + CLAVE + " = '" + campeonLiga.getIdEntrenador() + "';";
            persistencia.update(sql);
        }

        public CampeonLiga buscar(int idEntrenador) throws PersistenciaException {
            return (CampeonLiga) persistencia.buscarElemento(idEntrenador);

        }

        public void modificar(CampeonLiga campeonLiga) throws PersistenciaException {
            String sql = "UPDATE " + TABLA + " SET Region = '" + campeonLiga.getRegion() + "' WHERE "
                    + CLAVE + " = '" + campeonLiga.getIdEntrenador() + "';";
            persistencia.update(sql);
        }

    }

