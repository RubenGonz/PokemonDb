package es.iespuertolacruz.pokemon.modelo;

import es.iespuertolacruz.pokemon.excepciones.PersistenciaException;

/**
 * Clase principal de los driver de SqLite
 */
public class DdBbSqLite extends DdBb {

   //Declaramos variables

   private static final String DRIVER = "org.sqlite.JDBC";
   private static final String URL = "jdbc:sqlite:test.db";

   //Constructores

   /**
    * Constructor con todos los parametros pasados por el usuario
    *
    * @param nombreTabla nombre de la tabla
    * @param driver de SqLite
    * @param urlConexion de la conexion
    * @param usuario de la conexion
    * @param password contrasenia del usuario
    * @throws PersistenciaException con error controlado
    */
   public DdBbSqLite(String nombreTabla, String driver, String urlConexion, String usuario,
         String password) throws PersistenciaException {
      super(nombreTabla, driver, urlConexion, usuario, password);
   }

   /**
    * Constructor con parametros pasados por el usuario y por la clase
    *
    * @param nombreTabla nombre de la tabla
    * @param usuario de la conexion
    * @param password contrasenia del usuario
    * @throws PersistenciaException con error controlado
    */
   public DdBbSqLite(String nombreTabla, String usuario, String password)
         throws PersistenciaException {
      super(nombreTabla, DRIVER, URL, usuario, password);
   }

}
