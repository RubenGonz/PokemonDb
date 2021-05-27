package es.iespuertolacruz.pokemon.modelo;

import es.iespuertolacruz.pokemon.excepciones.PersistenciaException;

public class DdBbSqLite extends DdBb {

   private static final String DRIVER = "org.sqlite.JDBC";
   private static final String URL = "jdbc:sqlite:test.db";

   public DdBbSqLite(String nombreTabla, String driver, String urlConexion, String usuario,
         String password, String sqlCretate) throws PersistenciaException {
      super(nombreTabla, driver, urlConexion, usuario, password, sqlCretate);
   }

   public DdBbSqLite(String nombreTabla, String usuario, String password, String sqlCreate)
         throws PersistenciaException {
      super(nombreTabla, DRIVER, URL, usuario, password, sqlCreate);
   }

}
