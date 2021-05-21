package es.iespuertolacruz.pokemon.modelo;

import es.iespuertolacruz.pokemon.excepciones.PersistenciaException;

public class DdBbSqLite extends DdBbRefactorizado {

   private static final String DRIVER = "org.sqlite.JDBC";
   private static final String URL = "jdbc:sqlite:test.db";

   public DdBbSqLite(String nombreTabla, String clave, String driver, String urlConexion, String usuario,
         String password) throws PersistenciaException {
      super(nombreTabla, clave, driver, urlConexion, usuario, password);
   }

   public DdBbSqLite(String nombreTabla, String clave, String usuario, String password) throws PersistenciaException {
      super(nombreTabla, clave, DRIVER, URL, usuario, password);
   }

}
