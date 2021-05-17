package es.iespuertolacruz.pokemon.modelo;

import es.iespuertolacruz.pokemon.api.CampeonLiga;
import es.iespuertolacruz.pokemon.excepciones.PersistenciaException;

/**
 * Clase modelo que tranaja con la clase campeonliga
 */
public class CampeonLigaModelo {
   
        // Persistencia en MySql

        MySqlBdDd persistencia;

        // Constructores

        public CampeonLigaModelo() {
            try {
                persistencia = new MySqlBdDd("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/test", "minty",
                        "greatsqldb");
            } catch (PersistenciaException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        public void insertar(CampeonLiga  campeonLiga) throws PersistenciaException {
            persistencia.insertarCampeonLiga(campeonLiga);
        }

        public void eliminar(CampeonLiga campeonLiga) throws PersistenciaException {
            persistencia.eliminarCampeonLiga(campeonLiga);
        }

        public CampeonLiga buscar(int idEntrenador) throws PersistenciaException {
            return persistencia.obtenerCampeonLiga(idEntrenador);
        }

        public void modificar(CampeonLiga campeonLiga) throws PersistenciaException {
            persistencia.modificarCampeonLiga(campeonLiga);
        }

    }

